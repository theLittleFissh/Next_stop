package com.example.nextstop;

import static android.text.TextUtils.isEmpty;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class AgentHome extends AppCompatActivity {

    EditText app,mobile,address,room,cost;
    Button submit;

    ImageView imgupl;
    Uri imageUri;
    Bitmap bitmap;

    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;
    private UploadTask.TaskSnapshot taskSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_home);

        app = findViewById(R.id.apartName);
        mobile = findViewById(R.id.contactNumber);
        address = findViewById(R.id.address);
        cost = findViewById(R.id.price);
        submit =(Button) findViewById(R.id.submitbtn);
        room=findViewById(R.id.roomavailable);
        imgupl = findViewById(R.id.Viewimage);


        imgupl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Dexter.withActivity(AgentHome.this)
//                                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                                .withListener(new PermissionListener() {
//                                    @Override
//                                    public void onPermissionGranted(PermissionGrantedResponse response)
//                                    {
//                                        Intent intent =new Intent(Intent.ACTION_PICK);
//                                        intent.setType("image/*");
//                                        startActivityForResult(Intent.createChooser(intent,"select Image File"),1);
//                                    }
//
//                                    @Override
//                                    public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                                    }
//
//                                    @Override
//                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                                        token.continuePermissionRequest();
//                                    }
//                                }).check();

                        Intent intent =  new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);

                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        uploadtofirebase();

                        String App,Mobile,Address,Room,Cost;
                        App = app.getText().toString();
                        Mobile = mobile.getText().toString();
                        Address = address.getText().toString();
                        Room = room.getText().toString();
                        Cost = cost.getText().toString();


                        if(imageUri == null){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AgentHome.this);
                            builder1.setTitle("Alert !");
                            builder1.setMessage("Image can't selected ! Please Select Image.");
//                    builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });



                            AlertDialog alert11 = builder1.create();
                            alert11.show();

                        }
                        else{


                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference uploder = storage.getReference("Image1"+new Random().nextInt(50));

                            uploder.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                                    DatabaseReference root = rootNode.getReference("User");
                                                    userHelper helper = new userHelper(App,Mobile,Address,Room,Cost,uri.toString());

                                                    root.child(app.getText().toString()).setValue(helper);

                                                    app.setText("");
                                                    mobile.setText("");
                                                    address.setText("");
                                                    room.setText("");
                                                    cost.setText("");
                                                    imgupl.setImageResource(R.drawable.imageup);
                                                    Toast.makeText(AgentHome.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    })
                                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                            if(imageUri != null){


                                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                                        dialog.setMessage("Uploaded:"+(int)progress+"%");
                                                AlertDialog.Builder builder2 = new AlertDialog.Builder(AgentHome.this);
                                                builder2.setTitle("Alert !");
                                                builder2.setMessage("Uploaded:"+(int)progress+"%");
                                                //                    builder1.setCancelable(true);

                                                builder2.setPositiveButton(
                                                        "Ok",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.cancel();
                                                            }
                                                        });



                                                AlertDialog alert11 = builder2.create();
                                                alert11.show();
                                            }
//                                       dialog.dismiss();
                                        }
                                    });
                        }


                    }
                });





//        //Image Upload er kaj ekhane
//            uploadImage=findViewById(R.id.Uploadimage);
//
//        ActivityResultLauncher<Intent>activityResultLauncher=registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if(result.getResultCode()== Activity.RESULT_OK){
//                            Intent data = result.getData();
//                            uri=data.getData();
//                            uploadImage.setImageURI(uri);
//                        }
//                        else{
//                            Toast.makeText(AgentHome.this, "No Image Selected", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                }
//        );
//
//        uploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photopicker=new Intent(Intent.ACTION_PICK);
//                photopicker.setType("image/+");
//                activityResultLauncher.launch(photopicker);
//            }
//        });
//

        //Image Upload er kaj sesh






//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                String App = apartment.getText().toString();
//                String Mobile = mobileNumber.getText().toString();
//                String Address = address.getText().toString();
//                String Room = room.getText().toString();
//                String Cost = cost.getText().toString();
//
//
//                if (isEmpty(App)) {
//                    apartment.setError("This Field Cannot be Empty");
//                    apartment.requestFocus();
//                } else if (isEmpty(Mobile)) {
//                    mobileNumber.setError("This Field Cannot be Empty");
//                    mobileNumber.requestFocus();
//                } else if (isEmpty(Address)) {
//                    address.setError("This Field Cannot be Empty");
//                    address.requestFocus();
//                } else if (isEmpty(Room)) {
//                    room.setError("This Field Cannot be Empty");
//                    room.requestFocus();
//                } else if (isEmpty(Cost)) {
//                    cost.setError("This Field Cannot be Empty");
//                    cost.requestFocus();
//                }
//                else
//                {
//
//
//                    firebaseDatabase=FirebaseDatabase.getInstance();
//                    DatabaseReference root=firebaseDatabase.getReference("User");
//                    userHelper helper=new userHelper(App,Mobile,Address,Room,Cost);
//                    root.child(App).setValue(helper);
//                    Toast.makeText(AgentHome.this, "Succefully Inserted", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//
//
//            }
//
//
//        });
    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==1 && resultCode == RESULT_OK ){

            imageUri = data.getData();

            try {
                InputStream inputStream  = AgentHome.this.getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgupl.setImageBitmap(bitmap);

            }catch (Exception e)
            {

            }



        }
    }
//    private void uploadtofirebase()
//    {
//        final ProgressDialog dialog=new ProgressDialog(this);
//        dialog.setTitle("File Uploader");
//        dialog.show();
//
//            //declare again
//            app=(EditText)findViewById(R.id.apartName);
//            mobile=(EditText) findViewById(R.id.contactNumber);
//            address=(EditText) findViewById(R.id.address);
//            cost=(EditText)findViewById(R.id.price);
//            room=(EditText)findViewById(R.id.roomavailable);
//
////            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
////            DatabaseReference root=firebaseDatabase.getReference();
//
//            FirebaseStorage storage=FirebaseStorage.getInstance();
//            StorageReference uploader=storage.getReference("Image1"+new Random().nextInt(200));
//
//
//            uploader.putFile(filepath)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//                        {
//                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    dialog.dismiss();
//                                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//                                    DatabaseReference root=firebaseDatabase.getReference("User");
//
//                                    userHelper userhelper=new userHelper(app.getText().toString()
//                                            ,mobile.getText().toString()
//                                            ,address.getText().toString()
//                                            ,room.getText().toString()
//                                            ,cost.getText().toString(),
//                                                uri.toString());
//
//                                            root.child(app.getText().toString()).setValue(userhelper);
//
//                                            app.setText("");
//                                            mobile.setText("");
//                                            address.setText("");
//                                            room.setText("");
//                                            cost.setText("");
//                                            img.setImageResource(R.drawable.imageup);
//                                    Toast.makeText(AgentHome.this, "Uploaded", Toast.LENGTH_SHORT).show();
//
//                                }
//                            });
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot)
//                        {
//                            float percent=(100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
//                            dialog.setMessage("Uploaded :"+(int)percent+" %");
//                        }
//                    });
//
//    }

}
//last bracket
