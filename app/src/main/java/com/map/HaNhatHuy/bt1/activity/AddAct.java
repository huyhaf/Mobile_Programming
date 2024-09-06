package com.map.HaNhatHuy.bt1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.map.HaNhatHuy.bt1.R;
import com.map.HaNhatHuy.bt1.model.User;

public class AddAct extends AppCompatActivity {
    EditText txtUserName;
    EditText txtPassword;
    EditText txtFullName;
    EditText txtEmail;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add); // Set the layout defined in XML

        txtUserName = findViewById(R.id.editText_username);
        txtPassword = findViewById(R.id.editText_password);
        txtFullName = findViewById(R.id.editText_fullname);
        txtEmail = findViewById(R.id.editText_email);
        btnAdd =  findViewById(R.id.button_add);

        // Set the onClick listener for the login button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick(v);
            }
        });
    }

    public void onAddButtonClick(View v){
        User user = new User();
        user.setUserName(txtUserName.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        user.setFullName(txtFullName.getText().toString());
        user.setEmail(txtEmail.getText().toString());


    }
}
