package com.example.svakatha;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.grpc.internal.AbstractReadableBuffer;
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
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth =  FirebaseAuth.getInstance();
    //private EditText editTextPercentage;
    //private Button buttonChangeLikeDislike;
    private static final int CAMERA_PERMISSIONS_REQUEST = 2;
    private static final int CAMERA_IMAGE_REQUEST = 3;
    private static final String FILE_NAME = "temp.jpg";
    Uri uri;
    int a;
    public boolean r=false,s=false,t=false;
    //////////////////////////////////
    public int x,percentage2,totalpercentage=0;
    public String maindata,Check0,Check1,Check2,Check3,reference,str2,resMain,smallcase,style1;
    public Boolean str=false;
    ////////////////////////////////////
    public String res;
    Bitmap bitmap,pixelatedbitmap;
    String[] colours,colours1;
    String[] fourcolours,fourcolours1;
    String[] colourvalue = new String[20];
    String[] colourvalue1 = new String[4];
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
        /////////////////////////////////////////////////
        db.collection("users").document(mAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String received4 = documentSnapshot.getString("ratingChoiceDocName");
                if(received4==null)
                {

                }
                else {
                    Log.i("ALL", received4);
                    reference = received4;
                    x = Integer.parseInt(extractInt(reference));
                }

            }
        });

        //Male and Female

        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText2 = documentSnapshot.getString("Gender");
                        str=finalProfileText2.equals("FEMALE");
                        str2=String.valueOf(str);
                    }
                });

        //Style Fetch
        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText3 = documentSnapshot.getString("Business");
                        style1=finalProfileText3;
                    }
                });



        //Condition Checking
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if(str2.equals("false")){
                    //male
                    if(style1.equals("Casual")){
                        db.collection("Images").document("malefolder").get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        String finalProfileText2 = documentSnapshot.getString("CasualMale");
                                        Log.i("ALL",finalProfileText2);
                                        resMain=finalProfileText2;
                                    }
                                });


                    }
                    else if(style1.equals("Ethnic")){
                        db.collection("Images").document("malefolder").get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        String finalProfileText2 = documentSnapshot.getString("EthincMale");
                                        Log.i("ALL",finalProfileText2);
                                        resMain=finalProfileText2;
                                    }
                                });

                    }
                    else if(style1.equals("Wedding")){
                        db.collection("Images").document("malefolder").get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        String finalProfileText2 = documentSnapshot.getString("weddingMale");
                                        Log.i("ALL",finalProfileText2);
                                        resMain=finalProfileText2;
                                    }
                                });

                    }
                    else if(style1.equals("Formal")){
                        db.collection("Images").document("malefolder").get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        String finalProfileText2 = documentSnapshot.getString("formalMale");
                                        Log.i("ALL",finalProfileText2);
                                        resMain=finalProfileText2;
                                    }
                                });

                    }


                }

                else {
                    //female

                        if (style1.equals("Casual")) {
                            db.collection("Images").document("femalefolder").get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String finalProfileText2 = documentSnapshot.getString("casualFemale");
                                            Log.i("ALL", finalProfileText2);
                                            resMain = finalProfileText2;
                                        }
                                    });


                        } else if (style1.equals("Ethnic")) {
                            db.collection("Images").document("femalefolder").get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String finalProfileText2 = documentSnapshot.getString("printsFemale");
                                            Log.i("ALL", finalProfileText2);
                                            resMain = finalProfileText2;
                                        }
                                    });

                        } else if (style1.equals("Wedding")) {
                            db.collection("Images").document("femalefolder").get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String finalProfileText2 = documentSnapshot.getString("WeddingFemale");
                                            Log.i("ALL", finalProfileText2);
                                            resMain = finalProfileText2;
                                        }
                                    });

                        } else if (style1.equals("Formal")) {
                            db.collection("Images").document("femalefolder").get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String finalProfileText2 = documentSnapshot.getString("FormalFemale");
                                            Log.i("ALL", finalProfileText2);
                                            resMain = finalProfileText2;
                                        }
                                    });

                        }


                }
            }
        }, 800);




        ////////////////////////////////////////////////


        imageButtonCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewCapturedImage.setImageURI(null);
                textViewRatingImageDetails.setText(null);
                imageViewLikeDislike.setVisibility(View.VISIBLE);
                imageViewLike.setVisibility(View.INVISIBLE);
                textViewLikeDislikePercentage.setText(0+"%");
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");

        if (str.equals(""))
            return "-1";

        return str;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////



    public void savetoDB(int a) {
        String ratingpercent = Integer.toString(a);
        Map<String,String > rating=new HashMap<>();
        rating.put("Rating",ratingpercent);
        db.collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("RatingDetails").document("IMG" + x)
                .set(rating, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Map<String, String> rating = new HashMap<>();
                rating.put("ratingChoiceDocName", "IMG" + x);
                db.collection("users").document(mAuth.getCurrentUser().getUid()).set(rating, SetOptions.merge());
            }
        });

    }
    ////////////////////////////////////////////////////////
    //main Logic for string
    public void stringextract(String q){

        //String q="brown";
        String r="(?<="+q+"=)\\d+";
        Pattern p = Pattern.compile(r);
        Matcher m = p.matcher(resMain);
        if (m.find()) {
            int an= Integer.parseInt(m.group());
            totalpercentage=totalpercentage+an;


        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
                Toast.makeText(context, "PROCESSING",Toast.LENGTH_SHORT).show();
                Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
                uri = photoUri;
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);

                callCloudVision(bitmap , "FACE_DETECTION");
                imageViewCapturedImage.setImageURI(uri);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //override
    public void onActivityResult1() {

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
                            callCloudVision(bitmap1 , "LABEL_DETECTION");
                            //imageViewCapturedImage.setImageBitmap(bitmap1);
                        }
                    })
                    .make();
            imageViewCapturedImage.setImageURI(uri);



        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void callCloudVision(final Bitmap bitmap , String type) {
        // Switch text to loading
        //mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        try {
            //doing unchecked activity conversion as we're only calling it from one activity
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask((HostActivity) getActivity(), prepareAnnotationRequest(bitmap , type));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        StringBuilder message = new StringBuilder("Detected:\n\n");
        Log.d("Detected",message.toString());
        String my = "hello";
        // List<EntityAnnotation> labels = response.getResponses().get(0);
        List<FaceAnnotation> labels = response.getResponses().get(0).getFaceAnnotations();
        // Log.d("afterDetected",labels.toString());
        Log.d("value",my);
        if (labels != null) {
            for (FaceAnnotation label : labels) {
                message.append(String.format(Locale.US, "%.3f: %s", label.getDetectionConfidence(), label.getAngerLikelihood()));
                message.append("\n");
            }
        } else {
            message.append("Face NOT Detected");

        }

        return message.toString();
    }
    //overide -->  After Pixelation Code
    private static String convertResponseToString1(BatchAnnotateImagesResponse response) {
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
    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap , String type) throws IOException {
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
                labelDetection.setType(type);
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
        String ratingDocName="IMG";

        LableDetectionTask(HostActivity activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                if(r==false) {
                    Log.d(TAG, "created Cloud Vision request object, sending request");
                    BatchAnnotateImagesResponse response = mRequest.execute();
                    return convertResponseToString(response);
                }
                else {
                    Log.d(TAG, "created Cloud Vision request object, sending request");
                    BatchAnnotateImagesResponse response = mRequest.execute();
                    return convertResponseToString1(response);

                }

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
                String check5="Face NOT Detected";
                textViewRatingImageDetails.setText(res);
                Log.d("res.tolowercase", String.valueOf(res.toLowerCase().indexOf(check5.toLowerCase())));
                if (res.toLowerCase().indexOf(check5.toLowerCase()) == -1 && r==false ) {
                    Log.d("i am inside ","the code");
                    r=true;
                    Toast.makeText(context, "FACE RECOGNISED ", Toast.LENGTH_LONG).show();

                    onActivityResult1();


                }
                else if(r==true){
                    r=false;
                    ++x;


                    Log.d("res", String.valueOf(res));
                    int s=countLines(res);
                    colours=res.split("\\R", s);



                    Log.d("colorsAfter", String.valueOf(colours));
                    for (int i = 2; i <= s-1; i++) {
                        fourcolours = colours[i].split("\\s+", 0);
                        colourvalue[i - 2] = fourcolours[1];
                        smallcase=colourvalue[i-2].toLowerCase();
                        stringextract(smallcase);
                    }

                    totalpercentage=totalpercentage/10000000;

                    textViewtotal.setText(Integer.toString(totalpercentage));


                    textViewRatingImageDetails.setText(colourvalue[0] + "\n" + colourvalue[1] + "\n" + colourvalue[2] + "\n" + colourvalue[3]);
                    ///////////////////////////////////////////////////////////




                    Check0 = colourvalue[0];
                    Check1 = colourvalue[1];
                    Check2 = colourvalue[2];
                    Check3 = colourvalue[3];
                    if(x==1){

                        if(totalpercentage==0) {
                            final int percentage = new Random().nextInt(41) + 30;
                            a = percentage;
                            if (percentage < 50) {
                                imageViewLikeDislike.setVisibility(View.VISIBLE);
                                imageViewLike.setVisibility(View.INVISIBLE);
                                textViewLikeDislikePercentage.setText(percentage + "%");
                            } else {
                                imageViewLike.setVisibility(View.VISIBLE);
                                imageViewLikeDislike.setVisibility(View.INVISIBLE);
                                textViewLikeDislikePercentage.setText(percentage + "%");

                            }
                            savetoDB(a);
                        }
                        else {
                            a=totalpercentage;
                            if (totalpercentage < 50) {
                                imageViewLikeDislike.setVisibility(View.VISIBLE);
                                imageViewLike.setVisibility(View.INVISIBLE);
                                textViewLikeDislikePercentage.setText(totalpercentage + "%");
                            } else {
                                imageViewLike.setVisibility(View.VISIBLE);
                                imageViewLikeDislike.setVisibility(View.INVISIBLE);
                                textViewLikeDislikePercentage.setText(totalpercentage + "%");

                            }
                            savetoDB(a);
                        }
                    }



                    //////////////////////////////////////////////////////////


                    db.collection("users").document(currentUSer)
                            .collection("RatingDetails").document(ratingDocName + x)
                            .set(data, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Map<String, String> data = new HashMap<>();
                            data.put("ratingChoiceDocName", ratingDocName + x);
                            db.collection("users").document(currentUSer).set(data, SetOptions.merge());

                        }
                    });

                    mainprocess(x);

                }
                else{
                    Toast.makeText(context, "FACE NOT RECOGNISED ", Toast.LENGTH_LONG).show();
                    final int percentage3 = 0;
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(percentage3+"%");

                }
            }

        }

    }
    //////////////////////////////////////////////////////////////////////
    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onDestroy() {
        super.onDestroy();
        //uri = null;
    }
    public void mainprocess(int y){
        y=y-1;
        t=false;
        s=false;
        if(y==0) {


        }
        else {

            if (totalpercentage != 0) {
                a = totalpercentage;
                if (totalpercentage < 50) {
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(totalpercentage + "%");
                } else {
                    imageViewLike.setVisibility(View.VISIBLE);
                    imageViewLikeDislike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(totalpercentage + "%");

                }
                savetoDB(a);
            } else {

                for (int j = 1; j <= y; j++) {

                    int finalJ = j;
                    db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("RatingDetails").document("IMG" + j).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received = documentSnapshot.getString("RatingImageDetails");
                            Log.i("ALL", received);
                            maindata = received;
                            ///////////////////////////////////////////////////////////////////////
                            Log.d("maindata", String.valueOf(maindata));

                            colours1 = maindata.split("\\R", 7);
                            Log.d("colorsAfter", String.valueOf(colours1));
                            for (int i = 2; i <= 5; i++) {
                                fourcolours1 = colours1[i].split("\\s+", 0);
                                colourvalue1[i - 2] = fourcolours1[1];
                            }

                            ///////////////////////////////////////////////////////////


                            String ch0;
                            ch0 = colourvalue1[0];


                            //////////////////////////////////////////////////////////////////////
                            //maindata = maindata.replace("Pattern","");
                            //maindata = maindata.replace("Line","");
                            // maindata = maindata.replace("Symmetry","");
                            // maindata = maindata.replace("Square","");
                            //maindata = maindata.replace("Tints and Shades","");
                            //maindata = maindata.replace("Rectangle","");
                       /* Log.d("maindata",maindata);
                        String[] array = { Check0 , Check1 , Check2 , Check3};
                        int aditya = 0 ;
                        for(int rohan = 0 ; rohan <array.length; rohan++){
                            Log.d("maindata.toLowerCase][", String.valueOf(maindata.toLowerCase().contains(array[rohan])));
                            Log.d("maindat.tolowerCase",maindata.toLowerCase());
                            Log.d("array[rohan]",array[rohan]);
                            if(maindata.toLowerCase().contains(array[rohan].toLowerCase())){
                                aditya ++;
                            Log.d("aditya", String.valueOf(aditya));
                            }

                        }*/


                            if (ch0.toLowerCase().indexOf(Check0.toLowerCase()) != -1) {

                                if (t == false) {

                                    Log.d("aditya", "insideif");
                                    //Toast.makeText(context, "MATCH FOUND: " + finalJ, Toast.LENGTH_LONG).show();
                                    db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("RatingDetails").document("IMG" + finalJ).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                            String received1 = documentSnapshot.getString("Rating");
                                            Log.i("ALL", received1);
                                            String change = received1;
                                            percentage2 = Integer.parseInt(change);
                                            a = percentage2;
                                            if (percentage2 < 50) {
                                                imageViewLikeDislike.setVisibility(View.VISIBLE);
                                                imageViewLike.setVisibility(View.INVISIBLE);
                                                textViewLikeDislikePercentage.setText(percentage2 + "%");
                                            } else {
                                                imageViewLike.setVisibility(View.VISIBLE);
                                                imageViewLikeDislike.setVisibility(View.INVISIBLE);
                                                textViewLikeDislikePercentage.setText(percentage2 + "%");
                                            }
                                            //back to DB
                                            String ratingpercent = Integer.toString(a);
                                            Map<String, String> rating = new HashMap<>();
                                            rating.put("Rating", ratingpercent);

                                            db.collection("users").document(mAuth.getCurrentUser().getUid())
                                                    .collection("RatingDetails").document("IMG" + x)
                                                    .set(rating, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Map<String, String> rating = new HashMap<>();
                                                    rating.put("ratingChoiceDocName", "IMG" + x);
                                                    db.collection("users").document(mAuth.getCurrentUser().getUid()).set(rating, SetOptions.merge());
                                                }
                                            });
                                        }
                                    });
                                    t = true;
                                }

                            } else {
                                if (s == false) {
                                    final int percentage = new Random().nextInt(41) + 30;
                                    a = percentage;
                                    if (percentage < 50) {
                                        imageViewLikeDislike.setVisibility(View.VISIBLE);
                                        imageViewLike.setVisibility(View.INVISIBLE);
                                        textViewLikeDislikePercentage.setText(percentage + "%");
                                    } else {
                                        imageViewLike.setVisibility(View.VISIBLE);
                                        imageViewLikeDislike.setVisibility(View.INVISIBLE);
                                        textViewLikeDislikePercentage.setText(percentage + "%");

                                    }
                                    savetoDB(a);
                                }
                                s = true;
                                //Toast.makeText(context, "MATCH NOT FOUND: "+ finalJ, Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }

            }
        }
    }


}