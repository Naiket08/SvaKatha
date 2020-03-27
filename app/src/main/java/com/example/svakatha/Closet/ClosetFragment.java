package com.example.svakatha.Closet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.svakatha.Closet.Adapter.ClosetGridAdapter;
import com.example.svakatha.HostActivity;
import com.example.svakatha.PackageManagerUtils.PackageManagerUtils;
import com.example.svakatha.Closet.Listeners.ClosetFragmentListener;
import com.example.svakatha.PermissionUtils.PermissionUtils;
import com.example.svakatha.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClosetFragment extends Fragment implements ClosetFragmentListener {

    private static final String CLOUD_VISION_API_KEY = "AIzaSyBYZftL8rp5UhFUPMHM_1dJ-3tfqNVN34E";
    private static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;

    private static final String TAG = HostActivity.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    private static final int CAMERA_PERMISSIONS_REQUEST = 2;
    private static final int CAMERA_IMAGE_REQUEST = 3;

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    private List<DocumentSnapshot> gridImageList;
    private List<DocumentSnapshot> shuffledImageList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Context mContext;

    private AppCompatImageView ivShowcase;
    private GridView gridView;

    private ClosetGridAdapter closetGridAdapter;
    private int shuffledListIndex = 0;
    private int size;
    private View view;

    public ClosetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirebase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getContext();
        view = inflater.inflate(R.layout.fragment_closet,container,false);
        setupAddButton(view);
        AppCompatImageButton btnLeft = view.findViewById(R.id.btn_left);
        AppCompatImageButton btnRight = view.findViewById(R.id.btn_right);

        gridView = view.findViewById(R.id.gv_image);
        ivShowcase = view.findViewById(R.id.iv_showcase);
        closetGridAdapter = new ClosetGridAdapter(mContext);
        gridView.setAdapter(closetGridAdapter);

        btnLeft.setOnClickListener(
                v -> leftButtonClicked()
        );

        btnRight.setOnClickListener(
                v -> {
                    rightButtonClicked();
                }
        );

        String[] occasion = new String[] {"Select Occasion","Item 1", "Item 2", "Item 3", "Item 4"};
        Spinner occasionSpnr = view.findViewById(R.id.spnr_occasion);
        ArrayAdapter<String> occasionAdapter =
                new ArrayAdapter<>(
                        mContext,
                        R.layout.dropdown_menu_popup_item,
                        occasion);

        occasionSpnr.setAdapter(occasionAdapter);

        return view;

    }

    private void setupShowcaseImageView() {

        shuffledImageList = gridImageList;
        if (shuffledImageList != null && !shuffledImageList.isEmpty()) {
            Collections.shuffle(Collections.singletonList(shuffledImageList));

            size = shuffledImageList.size();
            setShowCaseImage(0);
        }

    }

    private void setShowCaseImage(int index){
        Picasso.get().load(shuffledImageList.get(index).getString("downloadUrl")).into(ivShowcase);
    }

    private void leftButtonClicked() {
        if (shuffledImageList != null && !shuffledImageList.isEmpty()) {
            if (shuffledListIndex == 0) {
                shuffledListIndex = size - 1;
                setShowCaseImage(shuffledListIndex);
            } else {
                setShowCaseImage(--shuffledListIndex);
            }
        }
    }

    private void rightButtonClicked() {
        if (shuffledImageList != null && !shuffledImageList.isEmpty()) {
            if (shuffledListIndex == size - 1) {
                shuffledListIndex = 0;
                setShowCaseImage(shuffledListIndex);
            } else {
                setShowCaseImage(++shuffledListIndex);
            }
        }
    }

    private void setupAddButton(View view) {
        AppCompatImageButton btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {

            /*FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ClosetDialog closetDialog = new ClosetDialog(mContext,getActivity(), this);
            closetDialog.show(ft,"ClosetDialog");*/

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder
                    .setMessage("Upload a Picture")
                    .setPositiveButton("Gallery", (dialog, which) -> startGalleryChooser())
                    .setNegativeButton("Camera", (dialog, which) -> startCamera());
            builder.create().show();
        });
    }
    private void startGalleryChooser() {
        if (PermissionUtils.requestPermission(getActivity(), GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    private void startCamera() {
        if (PermissionUtils.requestPermission(
                getActivity(),
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    private void setupImageGridView() {

        if(gridImageList != null) {
            closetGridAdapter.setImageList(gridImageList);
            closetGridAdapter.notifyDataSetChanged();
        }

    }

    private void initFirebase() {
        //Initializing Firebase Instance
        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        getDataFromDb();
    }

    private void getDataFromDb() {
        String currentUser=mAuth.getCurrentUser().getUid();
        db.collection("users").document(currentUser)
                .collection("ClosetDetails").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        gridImageList =queryDocumentSnapshots.getDocuments();
                        onImageListReceived();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //TODO: discuss what is to be done on failure
                        Toast.makeText(mContext, "Error while loading images", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Toast.makeText(mContext,"Uploading Image, please wait",Toast.LENGTH_LONG).show();
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Toast.makeText(mContext,"Uploading Image, please wait",Toast.LENGTH_LONG).show();
            Uri photoUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
        }
    }

    private void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri),
                                MAX_DIMENSION);

                callCloudVision(bitmap);


                mAuth=FirebaseAuth.getInstance();
                String currentUser=mAuth.getCurrentUser().getUid();
                StorageReference filePath=mStorageRef.child("UserClosetImages").child(currentUser).child(UUID.randomUUID().toString());
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("Status","Uploaded");
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("Status",uri.toString());
                                Map<String ,String> data=new HashMap<>();
                                data.put("downloadUrl",uri.toString());
                                Map<String,Object> data_time=new HashMap<>();
                                data_time.put("Time",new Timestamp(new Date()));
                                String currentUser=mAuth.getCurrentUser().getUid();
                                db.collection("users").document(currentUser)
                                        .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        String docName=documentSnapshot.getString("closetChoiceDocName");
                                        db.collection("users").document(currentUser)
                                                .collection("ClosetDetails").document(docName)
                                                .set(data,SetOptions.merge());
                                        db.collection("users").document(currentUser)
                                                .collection("ClosetDetails").document(docName)
                                                .set(data_time,SetOptions.merge());

//                                        db.collection("users").document(currentUser)
//                                                .collection("ClosetDetails").document(docName)
//                                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                                    @Override
//                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//
                                                        Toast.makeText(mContext,"Upload Successful",Toast.LENGTH_SHORT).show();
//                                                        gridImageList.add(documentSnapshot);
//                                                        shuffledImageList.add(documentSnapshot);
//                                                        onImageListReceived();
//                                                    }
//                                                });

                                        getDataFromDb();
                                    }
                                });
                            }
                        });
                    }
                });


            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(mContext, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(mContext, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
        //mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        try {
            //doing unchecked activity conversion as we're only calling it from one activity
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask((HostActivity) getActivity(), prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    private static String convertResponseToString(BatchAnnotateImagesResponse response) {
        StringBuilder message = new StringBuilder("Detected:\n\n");

        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
                message.append(String.format(Locale.US, "%.3f: %s", label.getScore(), label.getDescription()));
                message.append("\n");
            }
        } else {
            message.append("nothing");
        }

        return message.toString();
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = mContext.getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(mContext.getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature labelDetection = new Feature();
                labelDetection.setType("LABEL_DETECTION");
                labelDetection.setMaxResults(MAX_LABEL_RESULTS);
                add(labelDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    @Override
    public void onImageListReceived() {
        setupShowcaseImageView();
        setupImageGridView();
    }

    private File getCameraFile() {
        File dir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    private static class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<HostActivity> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;
        String userDocName;
        private FirebaseAuth mAuth=FirebaseAuth.getInstance();
        private StorageReference mStorageRef;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String closetDocName= UUID.randomUUID().toString();

        LableDetectionTask(HostActivity activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d(TAG, "created Cloud Vision request object, sending request");
                BatchAnnotateImagesResponse response = mRequest.execute();
                return convertResponseToString(response);

            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        protected void onPostExecute(String result) {
            HostActivity activity = mActivityWeakReference.get();
            if (activity != null && !activity.isFinishing()) {
                String currentUSer=mAuth.getCurrentUser().getUid();
                Map<String,String > data=new HashMap<>();
                data.put("AnalysisText",result);
                db.collection("users").document(currentUSer)
                        .collection("ClosetDetails").document(closetDocName)
                        .set(data, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Map<String,String>data=new HashMap<>();
                        data.put("closetChoiceDocName",closetDocName);
                        db.collection("users").document(currentUSer).set(data, SetOptions.merge());
                    }
                });
            }
        }

    }

}
