package com.map.HaNhatHuy.bt1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.map.HaNhatHuy.bt1.R;
import com.map.HaNhatHuy.bt1.model.User;

import java.io.Serializable;

public class UserHome extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);
        User user = (User) getIntent().getSerializableExtra("user");
        TextView welcomeStr = findViewById(R.id.welcomeTextView);
        welcomeStr.setText("Welcome " + user.getUsername());
        Button addButton = findViewById(R.id.addButton);
        Button searchButton = findViewById(R.id.searchButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, AddAct.class));
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserHome.this, SearchAct.class));
            }
        });
    }
}

