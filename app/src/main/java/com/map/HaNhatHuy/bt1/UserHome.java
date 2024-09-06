package com.map.HaNhatHuy.bt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.map.HaNhatHuy.bt1.activity.AddAct;
import com.map.HaNhatHuy.bt1.model.User;

import java.io.Serializable;
import java.util.ArrayList;

public class UserHome extends AppCompatActivity implements Serializable {

    Button btnAdd;
    Button btnEdit;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome); // Set the user home layout
        // Get the Intent that started this activity and extract the username
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        ArrayList<User> lstUser = (ArrayList<User>) intent.getSerializableExtra("lstUser");

        // Find the TextView and set the welcome message
        TextView welcomeText = findViewById(R.id.welcomeText);
        if (user.getUserName() != null) {a
            welcomeText.setText("Hello " + user.getUserName());
        } else {
            welcomeText.setText("Hello User");
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddViewButtonClick(v);
            }
        });
    }

    public void onAddViewButtonClick(View v){
        Intent intent = new Intent(this, AddAct.class);
        startActivity(intent);
    }
}

