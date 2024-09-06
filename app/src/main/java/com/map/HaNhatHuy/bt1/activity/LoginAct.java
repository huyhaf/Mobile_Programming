package com.map.HaNhatHuy.bt1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.map.HaNhatHuy.bt1.R;
import com.map.HaNhatHuy.bt1.UserHome;
import com.map.HaNhatHuy.bt1.model.User;

import java.util.ArrayList;

public class LoginAct extends AppCompatActivity {

    // Declare the EditText and Button fields
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;

    ArrayList<User>lstUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Set the layout defined in XML

        // Initialize the EditText and Button fields
        txtUsername = findViewById(R.id.usernameInput);
        txtPassword = findViewById(R.id.passwordInput);
        btnLogin = findViewById(R.id.loginButton);

        // Set the onClick listener for the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick(v);
            }
        });
    }

    // Method to handle the button click
    public void onLoginButtonClick(View view) {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        User user = new User(username,password);
        lstUser = new ArrayList<>();

        if (validateCredentials(user)) {
            // Create an Intent to start UserHome activity
            Intent intent = new Intent(this, UserHome.class);
            // Pass the username to the UserHome activity
            intent.putExtra("user", user);
            intent.putExtra("lstUser",lstUser);
            startActivity(intent);
        } else {
            txtUsername.setError("Invalid username or password");
        }
    }

    // A simple method to validate credentials (just a placeholder for your logic)
    private boolean validateCredentials(User user) {
        // Replace this with your actual validation logic
        return user.equals(new User("B21DCVT224","30032003"));
    }
}

