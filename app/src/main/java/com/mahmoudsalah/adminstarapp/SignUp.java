package com.mahmoudsalah.adminstarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class SignUp extends AppCompatActivity implements AsyncCallback<BackendlessUser> {
    EditText emailText,usernameText,passwordText,confirmpasswordText,firstnameText,lastnameText;
    Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailText = findViewById(R.id.emailText);
        usernameText = findViewById(R.id.usernameEditText);
        passwordText = findViewById(R.id.passwordEditText);
        confirmpasswordText = findViewById(R.id.confirmpasswordText);
        firstnameText = findViewById(R.id.firstnameText);
        lastnameText = findViewById(R.id.lastnameText);




    }

    public void signup(View view) {
        if (emailText.getText().toString().equals("") || usernameText.getText().toString().equals("") || passwordText.getText().toString().equals("")
                || firstnameText.getText().toString().equals("") || lastnameText.getText().toString().equals("")) {
            Toast.makeText(this, "Please Fill the blanks", Toast.LENGTH_SHORT).show();
        } else {
            if (!passwordText.getText().toString().equals(confirmpasswordText.getText().toString())){
                Toast.makeText(this, "The password don't match", Toast.LENGTH_SHORT).show();
            }else {
                BackendlessUser user = new BackendlessUser();
                user.setEmail(emailText.getText().toString());
                user.setPassword(passwordText.getText().toString());
                user.setProperty("firstName", firstnameText.getText().toString());
                user.setProperty("lastName", lastnameText.getText().toString());
                user.setProperty("username", usernameText.getText().toString());


                Backendless.UserService.register(user, this);
            }
        }
    }
    @Override
    public void handleResponse(BackendlessUser response) {
        Toast.makeText(this, "Successed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleFault(BackendlessFault fault) {
        if (fault.getCode().equals("3033")){
            Toast.makeText(this, "This account exists", Toast.LENGTH_LONG).show();

        }else
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
