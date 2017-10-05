package com.example.isaachutchinson1.highlanderbuy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * @author Isaac Hutchinson
 *         09/29/2017
 *         Source for mRegEmail registration: https://youtu.be/8VDCC26XGwY
 *         With slight name modifications.
 */
public class RegistrationActivity extends AppCompatActivity {

    private TextInputLayout userName;
    private TextInputLayout mRegEmail;
    private TextInputLayout password;
    private Button createBtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ProgressBar accountCreation;
    private HashMap<String, String> userDataCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userDataCreation = new HashMap<>();
        myRef = database.getReference();

        userName = (TextInputLayout) findViewById(R.id.registration_username);
        mRegEmail = (TextInputLayout) findViewById(R.id.registration_email);
        password = (TextInputLayout) findViewById(R.id.registration_password);
        createBtn = (Button) findViewById(R.id.registration_createBtn);
        accountCreation = (ProgressBar) findViewById(R.id.registration_progress_bar_account);
        accountCreation.setVisibility(View.INVISIBLE);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameIn = userName.getEditText().getText().toString();
                String emailIn = mRegEmail.getEditText().getText().toString();
                String passwordIn = password.getEditText().getText().toString();

                if (!TextUtils.isEmpty(emailIn) & !TextUtils.isEmpty(passwordIn)
                        & !TextUtils.isEmpty(usernameIn)) {
                    accountCreation.setVisibility(View.VISIBLE);
                    email_register_user(usernameIn, emailIn, passwordIn);
                } else if (TextUtils.isEmpty(emailIn) & TextUtils.isEmpty(passwordIn)) {
                    Toast.makeText(RegistrationActivity.this, "Email address and password is required! ", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(emailIn)) {
                    Toast.makeText(RegistrationActivity.this, "Email address is required!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(passwordIn)) {
                    Toast.makeText(RegistrationActivity.this, "Password is required!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(usernameIn)) {
                    Toast.makeText(RegistrationActivity.this, "Username is required!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Unknown Error! Contact developer", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void email_register_user(final String usernameIn, final String emailIn, String passwordIn) {
        mAuth.createUserWithEmailAndPassword(emailIn, passwordIn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    myRef = database.getReference().child("Users").child(mAuth.getCurrentUser().getUid());

                    userDataCreation.put("Name", " ");
                    userDataCreation.put("Username", usernameIn);
                    userDataCreation.put("Email", emailIn);
                    userDataCreation.put("User_Image", " ");
                    userDataCreation.put("Fav_list", "Underdev");
                    userDataCreation.put("Active_Listings_List", " ");
                    userDataCreation.put("Sold_Listings_List", " ");

                    myRef.setValue(userDataCreation);
                    accountCreation.setVisibility(View.INVISIBLE);
                    Intent mainActivityIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                    //This prevents the user from being able to hit the back button.
                    Toast.makeText(RegistrationActivity.this, "In", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
