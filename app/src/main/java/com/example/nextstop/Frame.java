package com.example.nextstop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Frame extends AppCompatActivity {

    BottomNavigationView bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        bottom=findViewById(R.id.bottom_nevi);


        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id= item.getItemId();

                if(id==R.id.navi_home)
                {
                    load(new UserHomeFragment(),true);
                }
                else if(id==R.id.navi_food)
                {

                    load(new UserFoodFragment(),false);
                }
                else
                {
                    load(new UserProfileFragment(),false);
                }



                return true;
            }


        });

        bottom.setSelectedItemId(R.id.navi_home);





    }

     public void load(Fragment fragment,Boolean flag)
     {
         FragmentManager fragmentManager=getSupportFragmentManager();
         FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

         if (flag) {
             fragmentTransaction.add(R.id.frame,fragment);
         }
         else
         {
             fragmentTransaction.replace(R.id.frame,fragment);
         }
         fragmentTransaction.commit(); //start



     }
}