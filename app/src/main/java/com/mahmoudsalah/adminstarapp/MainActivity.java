package com.mahmoudsalah.adminstarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    TextView signUpTextView;
    Button loginInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpTextView = findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        Backendless.initApp(this, "6E91C934-6FFA-3180-FFCE-0B10BF41C200", "D3CFBAEA-7401-DF7A-FF29-7BFD47F43700");
        BackendlessUser user = new BackendlessUser();


    }

    public void login(View view) {
        if (usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
            Toast.makeText(this, "Please Fill the blanks", Toast.LENGTH_SHORT).show();
        } else {

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            Backendless.UserService.login(username, password, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {
                    Toast.makeText(MainActivity.this, "Logging", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,AddStar.class);
                    startActivity(intent);

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    if (fault.getCode().equals("3003")) {
                        Toast.makeText(MainActivity.this, "Invalid login or password.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error "+fault.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });


        }
    }
}
