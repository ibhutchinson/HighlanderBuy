package com.example.isaachutchinson1.highlanderbuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Isaac Hutchinson
 *         09/29/2017
 *         Source for mRegEmail registration: https://youtu.be/8VDCC26XGwY
 *         With slight name modifications.
 */
public class StartActivity extends AppCompatActivity {

    private Button mEmailRegButton;
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mEmailRegButton = (Button) findViewById(R.id.start_reg_button);
        mEmailSignInButton = (Button) findViewById(R.id.start_email_sign_in);

        mEmailRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration_intent = new Intent(StartActivity.this, RegistrationActivity.class);
                startActivity(registration_intent);
            }
        });
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration_intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(registration_intent);
            }
        });

    }
}
