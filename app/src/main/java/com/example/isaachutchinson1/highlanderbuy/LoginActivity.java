package com.example.isaachutchinson1.highlanderbuy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Isaac Hutchinson
 *         <p>
 *         Source: https://www.youtube.com/watch?v=6Iy7crsnVhA&list=PLGCjwl1RrtcSi2oV5caEVScjkM6r3HO9t
 */
public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button mLoginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mLoginEmail = (TextInputLayout) findViewById(R.id.login_email);
        mLoginPassword = (TextInputLayout) findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.login_loginBtn);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cMLoginEmail = mLoginEmail.getEditText().getText().toString();
                String cMLoginPassword = mLoginPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(cMLoginEmail) & !TextUtils.isEmpty(cMLoginPassword)) {
                    attemptLogin(cMLoginEmail, cMLoginPassword);
                } else if (TextUtils.isEmpty(cMLoginEmail) & TextUtils.isEmpty(cMLoginPassword)) {
                    Toast.makeText(LoginActivity.this, "Email address and password is required! ", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(cMLoginEmail)) {
                    Toast.makeText(LoginActivity.this, "Email address is required!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(cMLoginPassword)) {
                    Toast.makeText(LoginActivity.this, "Password is required!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Unknown Error! Contact developer", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void attemptLogin(String cMLoginEmail, String cMLoginPassword) {
        mAuth.signInWithEmailAndPassword(cMLoginEmail, cMLoginPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent toMainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(toMainActivityIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
