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

import com.example.nextstop.models.FoodModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.Random;

public class Agent_food_input extends AppCompatActivity {

    EditText foodName,foodPrice,foodProviderName;
    Button foodInserted;

    ImageView foodU_IMG,backbutton;
    Uri imageUri;
    Bitmap bitmap;

    private UploadTask.TaskSnapshot taskSnapshot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_food_input);

        foodName=findViewById(R.id.food_name);
        foodPrice=findViewById(R.id.food_price);
        foodProviderName=findViewById(R.id.food_provider_name);
        foodU_IMG=findViewById(R.id.foodViewimage);
        backbutton=findViewById(R.id.agent_food_input_backbtn);

        foodInserted=findViewById(R.id.foodsubmitbtn);

        //backbutton

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Agent_food_input.this,AgentFirstHome.class);
                startActivity(intent);
                finish();
            }
        });


        //image er kaj
        foodU_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);

            }
        });
         foodInserted.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String foodname,foodprice,providerName;

                 foodname=foodName.getText().toString();
                 foodprice=foodPrice.getText().toString();
                 providerName=foodProviderName.getText().toString();


                 if(imageUri == null){
                     AlertDialog.Builder builder1 = new AlertDialog.Builder(Agent_food_input.this);
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
                                             DatabaseReference root = rootNode.getReference("FoodInfo");
                                             FoodModel foodModel = new FoodModel (foodname,providerName,foodprice,uri.toString());

                                             root.child(foodName.getText().toString()).setValue(foodModel);

                                             foodName.setText("");
                                             foodPrice.setText("");
                                             foodProviderName.setText("");

                                             foodU_IMG.setImageResource(R.drawable.imageup);
                                             Toast.makeText(Agent_food_input.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
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
                                         AlertDialog.Builder builder2 = new AlertDialog.Builder(Agent_food_input.this);
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

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==1 && resultCode == RESULT_OK ){

            imageUri = data.getData();

            try {
                InputStream inputStream  = Agent_food_input.this.getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                foodU_IMG.setImageBitmap(bitmap);

            }catch (Exception e)
            {

            }



        }


    }



    }
