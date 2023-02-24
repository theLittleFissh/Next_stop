package com.example.nextstop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.example.nextstop.models.userHelper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgentHome extends AppCompatActivity {

    EditText app,mobile,address,room,cost,description;
    Button submit;

    ImageView imgupl;
    Uri imageUri;
    Bitmap bitmap;

    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;
    private UploadTask.TaskSnapshot taskSnapshot;

    @SuppressLint("MissingInflatedId")
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
        description=findViewById(R.id.hostel_descriptions);
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
//                        //new code
//                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
//                        //
                        startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);

                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        uploadtofirebase();

                        String App,Mobile,Address,Room,Cost,Description;
                        App = app.getText().toString();
                        Mobile = mobile.getText().toString();
                        Address = address.getText().toString();
                        Room = room.getText().toString();
                        Cost = cost.getText().toString();
                        Description=description.getText().toString();


                        //regx


                        String phoneRegex = "^(?:\\+88|88)?01[3-9]\\d{8}$";
                        Pattern pattern = Pattern.compile(phoneRegex);
                        Matcher matcher = pattern.matcher(Mobile);




                        //regx sesh



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

                        else if (!matcher.matches()) {
                            // Not a valid number
                            mobile.setError("Not a valid number");
                            mobile.requestFocus();
                        }
//                        else if(matcher.matches()) {
//                            // Valid number
//                            Toast.makeText(AgentHome.this, "Valid", Toast.LENGTH_LONG).show();
//                        }

                        else{


                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference uploder = storage.getReference("Image1"+new Random().nextInt(250));//initial=50chilo

                            uploder.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                                                    DatabaseReference root = rootNode.getReference("User");
                                                    userHelper helper = new userHelper(App,Mobile,Address,Room,Cost,Description,uri.toString());

                                                    root.child(app.getText().toString()).setValue(helper);

                                                    app.setText("");
                                                    mobile.setText("");
                                                    address.setText("");
                                                    room.setText("");
                                                    cost.setText("");
                                                    description.setText("");
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


}
//last bracket
