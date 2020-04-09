package com.example.svakatha;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.svakatha.Closet.ClosetFragment;
import com.example.svakatha.PackageManagerUtils.PackageManagerUtils;
import com.example.svakatha.PermissionUtils.PermissionUtils;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import nl.dionsegijn.pixelate.OnPixelateListener;
import nl.dionsegijn.pixelate.Pixelate;

import static android.app.Activity.RESULT_OK;

public class RatingFragment extends Fragment {

    private static final String CLOUD_VISION_API_KEY = "AIzaSyBYZftL8rp5UhFUPMHM_1dJ-3tfqNVN34E";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;

    private static final String TAG = HostActivity.class.getSimpleName();
    private Context context;
    private ImageView imageViewCapturedImage,imageViewLikeDislike,imageViewLike;
    private TextView textViewLikeDislikePercentage,textViewRatingImageDetails;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth =  FirebaseAuth.getInstance();
    //private EditText editTextPercentage;
    //private Button buttonChangeLikeDislike;
    private static final int CAMERA_PERMISSIONS_REQUEST = 2;
    private static final int CAMERA_IMAGE_REQUEST = 3;
    private static final String FILE_NAME = "temp.jpg";
    Uri uri;
    int a;
    String res;
    Bitmap bitmap,pixelatedbitmap;

    public RatingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_rating,container,false);
        imageViewCapturedImage = (ImageView) view.findViewById(R.id.imageViewCapturedImage);
        imageViewLikeDislike = (ImageView) view.findViewById(R.id.imageViewLikeDislike);
        ImageButton imageButtonCameraButton = (ImageButton) view.findViewById(R.id.imageButtonCameraButton);
        textViewLikeDislikePercentage = (TextView) view.findViewById(R.id.textViewLikeDislikePercentage);
        textViewRatingImageDetails = (TextView) view.findViewById(R.id.textViewRatingImageDetails);
//        editTextPercentage = (EditText) view.findViewById(R.id.editTextPercentge);
//        buttonChangeLikeDislike = (Button) view.findViewById(R.id.buttonChangeLikeDislike);
        imageViewLike = (ImageView) view.findViewById(R.id.imageViewLike);


        imageButtonCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });

        String[] selectstyle = new String[] {"Select Style","Casual", "Business Wear", "Party Wear", "Formal"};
        Spinner selectstylespnr = view.findViewById(R.id.spnr_selectstyle);
        ArrayAdapter<String> occasionAdapter =
                new ArrayAdapter<>(
                        context,
                        R.layout.dropdown_menu_popup_item,
                        selectstyle);

        selectstylespnr.setAdapter(occasionAdapter);
        //imageViewCapturedImage.setImageBitmap(pixelatedbitmap);
        imageViewCapturedImage.setImageURI(uri);
        if(a<50){
            imageViewLikeDislike.setVisibility(View.VISIBLE);
            imageViewLike.setVisibility(View.INVISIBLE);
            textViewLikeDislikePercentage.setText(a+"%");
        }
        else{
            imageViewLike.setVisibility(View.VISIBLE);
            imageViewLikeDislike.setVisibility(View.INVISIBLE);
            textViewLikeDislikePercentage.setText(a+"%");
        }
        textViewRatingImageDetails.setText(res);

        return view;
    }
    private void startCamera() {
        if (PermissionUtils.requestPermission(
                getActivity(),
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    private File getCameraFile() {
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            try {
                Toast.makeText(context, "Image Captured", Toast.LENGTH_LONG).show();
                Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
                uri = photoUri;
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                new Pixelate(bitmap)
                        .setDensity(8)
                        .setListener(new OnPixelateListener() {
                            @Override
                            public void onPixelated(Bitmap bitmap1, int density) {
                                //pixelatedbitmap = bitmap1;
                                callCloudVision(bitmap1);
                                //imageViewCapturedImage.setImageBitmap(bitmap1);
                            }
                        })
                        .make();
                imageViewCapturedImage.setImageURI(uri);
                final int percentage = new Random().nextInt(41) + 30;
                a = percentage;

                if(percentage<50){
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(percentage+"%");
                }
                else{
                    imageViewLike.setVisibility(View.VISIBLE);
                    imageViewLikeDislike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(percentage+"%");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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

                        String packageName = context.getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(context.getPackageManager(), packageName);

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

    private class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<HostActivity> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;
        String userDocName;
        private FirebaseAuth mAuth=FirebaseAuth.getInstance();
        private StorageReference mStorageRef;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String ratingDocName= UUID.randomUUID().toString();

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
                data.put("RatingImageDetails",result);
                res = result;
                textViewRatingImageDetails.setText(res);
                db.collection("users").document(currentUSer)
                        .collection("RatingDetails").document(ratingDocName)
                        .set(data, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Map<String,String>data=new HashMap<>();
                        data.put("ratingChoiceDocName",ratingDocName);
                        db.collection("users").document(currentUSer).set(data, SetOptions.merge());
                    }
                });
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //uri = null;
    }
}
