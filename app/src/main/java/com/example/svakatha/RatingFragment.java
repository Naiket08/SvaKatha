package com.example.svakatha;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

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
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class RatingFragment extends Fragment {


    //////////////////////////////////////////////////////////
    public static final String MODEL_PATH = "model.tflite";
    public static final boolean QUANT = true;
    public static final String LABEL_PATH = "dict.txt";
    public static final int INPUT_SIZE = 224;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 4192;
    public static final int CAMERA_REQUEST_CODE = 228;

    private Classifier classifier;

    private Executor executor = Executors.newSingleThreadExecutor();
    ///////////////////////////////////////////////////////////

    private static final String CLOUD_VISION_API_KEY = "AIzaSyBYZftL8rp5UhFUPMHM_1dJ-3tfqNVN34E";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;

    private static final String TAG = HostActivity.class.getSimpleName();
    private Context context;
    private Button upload;
    private ImageView imageViewCapturedImage,imageViewLikeDislike,imageViewLike;
    private TextView textViewLikeDislikePercentage,textViewRatingImageDetails;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth =  FirebaseAuth.getInstance();
    //private EditText editTextPercentage;
    //private Button buttonChangeLikeDislike;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    private static final String FILE_NAME = "temp.jpg";
    Uri uri;
    Bitmap B1;
    int a;
    public boolean r=false,s=false,t=false;
    //////////////////////////////////
    public int x,percentage2,totalpercentage=0,an=0;
    public String maindata,Check0,Check1,Check2,Check3,reference,smallcase,Choice="",str4="",str5="",str6="",str7="",condition="";
    public Boolean str=true,str3=true,str9=true;
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
//      editTextPercentage = (EditText) view.findViewById(R.id.editTextPercentge);
//      buttonChangeLikeDislike = (Button) view.findViewById(R.id.buttonChangeLikeDislike);
        imageViewLike = (ImageView) view.findViewById(R.id.imageViewLike);
         upload =(Button)view.findViewById(R.id.upload);
        /////////////////////////////////////////////////

        //Male and Female

        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText2 = documentSnapshot.getString("Gender");
                        str=finalProfileText2.equals("MALE");
                        str4=String.valueOf(str);
                    }
                });

        //Style Fetch

        String[] selectstyle = new String[] {"Select Style","Dinner party","A business dinner or a company party","Family Get-Together","College","Cocktail party","Business Formal","Religious Ceremony","Interview","A Night at the Theater","Office","Wedding"};
        Spinner selectstylespnr = view.findViewById(R.id.spnr_selectstyle);
        ArrayAdapter<String> occasionAdapter =
                new ArrayAdapter<>(
                        context,
                        R.layout.dropdown_menu_popup_item,
                        selectstyle);

        selectstylespnr.setAdapter(occasionAdapter);
        selectstylespnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Choice = selectstylespnr.getSelectedItem().toString();
                datafetch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Condition Checking

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(str3 && Choice!=null  && !Choice.equals("Select Style") && str4!=null){
                    // invoke the image gallery using an implict intent.
                    str9=true;
                    datafetch();

                    imageViewCapturedImage.setImageBitmap(null);
                    textViewRatingImageDetails.setText(null);
                    textViewLikeDislikePercentage.setText(null);
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);

                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                    // where do we want to find the data?
                    File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String pictureDirectoryPath = pictureDirectory.getPath();
                    // finally, get a URI representation
                    Uri data = Uri.parse(pictureDirectoryPath);

                    // set the data and type.  Get all image types.
                    photoPickerIntent.setDataAndType(data, "image/*");

                    // we will invoke this activity, and get something back from it.
                    startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST );
                }
                else if(Choice==null  || Choice.equals("Select Style")){
                    final Toast toast = Toast.makeText(context, "select your style", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);
                }else{
                    final Toast toast = Toast.makeText(context, "Wait Until Process Finished", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);
                }
            }
        });

///////////////////
        initTensorFlowAndLoadModel();
        ////////////////////////////////////////////////


    imageButtonCameraButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

             if(str3 && Choice!=null  && !Choice.equals("Select Style") && str4!=null){
                 str9=true;
            imageViewCapturedImage.setImageURI(null);
            textViewRatingImageDetails.setText(null);
            textViewLikeDislikePercentage.setText(null);
            imageViewLikeDislike.setVisibility(View.VISIBLE);
            imageViewLike.setVisibility(View.INVISIBLE);
            datafetch();
            str3=false;
                 try {
                     startCamera();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             else if(Choice==null  || Choice.equals("Select Style")){
                 final Toast toast = Toast.makeText(context, "select your style", Toast.LENGTH_SHORT);
                 toast.show();

                 Handler handler2 = new Handler();
                 handler2.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         toast.cancel();
                     }
                 }, 500);
             }else{
                 final Toast toast = Toast.makeText(context, "Wait Until Process Finished", Toast.LENGTH_SHORT);
                 toast.show();

                 Handler handler2 = new Handler();
                 handler2.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         toast.cancel();
                     }
                 }, 500);
             }



        }
    });

    if(str7.equals("camara")){
        imageViewCapturedImage.setImageURI(uri);}
    else {
        imageViewCapturedImage.setImageBitmap(B1);
    }
    textViewRatingImageDetails.setText(str5);
        //imageViewCapturedImage.setImageBitmap(pixelatedbitmap);

        return view;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////////////
public void onImage() throws IOException {
    Toast.makeText(context, "INSIDE ONIMAGE", Toast.LENGTH_SHORT);
    Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
    uri = photoUri;
    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);

    bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);

    final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);

    String s1=String.valueOf(results);
    str5=s1.replaceAll("\\(", "").replaceAll("\\)","");
    textViewRatingImageDetails.setText(str5);
}



    public void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getContext().getAssets(),
                            MODEL_PATH,
                            LABEL_PATH,
                            INPUT_SIZE,
                            QUANT);
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////

public void datafetch(){
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            //Do something after 100ms
            if(str){
                //male
                if(Choice.equals("Dinner party")||Choice.equals("A business dinner or a company party")||Choice.equals("Family Get-Together")||Choice.equals("College")||Choice.equals("Cocktail party")||Choice.equals("Business Formal")||Choice.equals("A Night at the Theater")||Choice.equals("Office")){

                    str6="casual_good";
                    stringextract();
                    if(condition.equals("you could do better...")&&Choice.equals("Cocktail party")){
                        str6="wedding_good";
                        stringextract();

                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("Dinner party")){
                        str6="ethnic_good";
                        stringextract();


                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("A business dinner or a company party")){
                        str6="formal_good";
                        stringextract();

                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("Family Get-Together")){
                        str6="ethnic_good";
                        stringextract();
                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("Office")){
                        str6="formal_good";
                        stringextract();
                    }

                }
                else if(Choice.equals("Religious Ceremony")){
                    str6="ethnic_good";
                    stringextract();
                }
                else if(Choice.equals("Wedding")){
                    str6="wedding_good";
                    stringextract();

                }
                else if(Choice.equals("Interview")){

                    str6="formal_good";
                    stringextract();

                }



            }

            else {
                //female

                if(Choice.equals("Dinner party")||Choice.equals("A business dinner or a company party")||Choice.equals("Family Get-Together")||Choice.equals("College")||Choice.equals("Cocktail party")||Choice.equals("Business Formal")||Choice.equals("A Night at the Theater")||Choice.equals("Office")){

                    str6="casual_good";
                    stringextract();
                    if(condition.equals("you could do better...")&&Choice.equals("Cocktail party")){
                        str6="wedding_good";
                        stringextract();

                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("Dinner party")){
                        str6="ethnic_good";
                        stringextract();


                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("A business dinner or a company party")){
                        str6="formal_good";
                        stringextract();

                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("Family Get-Together")){
                        str6="ethnic_good";
                        stringextract();
                    }
                    else if(condition.equals("you could do better...")&&Choice.equals("Office")){
                        str6="formal_good";
                        stringextract();
                    }

                }
                else if(Choice.equals("Religious Ceremony")){
                    str6="ethnic_good";
                    stringextract();
                }
                else if(Choice.equals("Wedding")){
                    str6="wedding_good";
                    stringextract();

                }
                else if(Choice.equals("Interview")){

                    str6="formal_good";
                    stringextract();

                }




            }

        }
    }, 100);

}


    ////////////////////////////////////////////////////////
    //main Logic for string
    public void stringextract(){

        //String q="brown";
        String r="(?<="+str6+" )\\d+";
        Pattern p = Pattern.compile(r);
        Matcher m = p.matcher(str5);
        if (m.find()) {

            if(str9)
            {
                imageViewLikeDislike.setVisibility(View.VISIBLE);
                imageViewLike.setVisibility(View.INVISIBLE);


            }
        else {
                an = Integer.parseInt(m.group());

                if (an <= 50) {
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText("you could do better...");


                } else {
                    imageViewLike.setVisibility(View.VISIBLE);
                    imageViewLikeDislike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText("you look amazing..!");

                }

            }

        }
        else
        {   an=0;
            imageViewLikeDislike.setVisibility(View.VISIBLE);
            imageViewLike.setVisibility(View.INVISIBLE);
            description();
        }
        condition=textViewLikeDislikePercentage.getText().toString();
    }

    public void description(){

        if(str5.isEmpty() || str9){


        }
        else
        {
            textViewLikeDislikePercentage.setText("you could do better...");

        }




    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void startCamera() throws IOException {
        if (PermissionUtils.requestPermission(
                getActivity(),
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            str3=true;
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
            str9=false;
            str7="camara";
            InputStream inputStream;
            try {

                final Toast toast = Toast.makeText(context, "PROCESSING", Toast.LENGTH_SHORT);
                toast.show();

                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1500);
                str3=false;
                Toast.makeText(context, "Image Captured",Toast.LENGTH_SHORT).show();
               // Toast.makeText(context, "PROCESSING",Toast.LENGTH_SHORT).show();
                Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
                uri = photoUri;
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                callCloudVision(bitmap , "FACE_DETECTION");
                imageViewCapturedImage.setImageURI(uri);
                inputStream =getContext().getContentResolver().openInputStream(photoUri);

                // get a bitmap from the stream.
                Bitmap image = BitmapFactory.decodeStream(inputStream);


                // show the image to the user
                image = Bitmap.createScaledBitmap(image, INPUT_SIZE, INPUT_SIZE, false);
                final List<Classifier.Recognition> results = classifier.recognizeImage(image);
                String s1=String.valueOf(results);
                str5=s1.replaceAll("\\(", "").replaceAll("\\)","");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            if (requestCode == IMAGE_GALLERY_REQUEST && resultCode == RESULT_OK) {
                str9=false;
                str7="gallary";

                final Toast toast = Toast.makeText(context, "PROCESSING", Toast.LENGTH_SHORT);
                toast.show();

                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1500);
                str3=false;
                Toast.makeText(context, "Inside code gallary", Toast.LENGTH_SHORT);
            // the address of the image on the SD Card.
            Uri imageUri = data.getData();

            // declare a stream to read the image data from the SD Card.
            InputStream inputStream1;

            // we are getting an input stream, based on the URI of the image.
            try {
                inputStream1 =getContext().getContentResolver().openInputStream(imageUri);

                // get a bitmap from the stream.
                Bitmap image = BitmapFactory.decodeStream(inputStream1);
                callCloudVision(image , "FACE_DETECTION");
                B1=image;

                // show the image to the user
                imageViewCapturedImage.setImageBitmap(image);
                image = Bitmap.createScaledBitmap(image, INPUT_SIZE, INPUT_SIZE, false);
                final List<Classifier.Recognition> results = classifier.recognizeImage(image);
                String s1=String.valueOf(results);
                str5=s1.replaceAll("\\(", "").replaceAll("\\)","");


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////

    //override


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

               // textViewRatingImageDetails.setText(res);
                Log.d("res.tolowercase", String.valueOf(res.toLowerCase().indexOf(check5.toLowerCase())));
                if (res.toLowerCase().indexOf(check5.toLowerCase()) == -1 && r==false ) {
                    Log.d("i am inside ","the code");
                    final Toast toast = Toast.makeText(context, "FACE RECOGNISED", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 2000);
                    textViewRatingImageDetails.setText(str5);
                    stringextract();
                    str3=true;
                }
                else{
                    final Toast toast = Toast.makeText(context, "FACE NOT RECOGNISED ", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 2000);
                    final int percentage3 = 0;
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);
                    str5="";
                    str3=true;

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



}