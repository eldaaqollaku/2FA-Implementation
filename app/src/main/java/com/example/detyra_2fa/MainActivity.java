package com.example.detyra_2fa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.email_input);
        Button sendOTPButton = findViewById(R.id.login_button);

        sendOTPButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                return;
            }


            String generatedOTP = generateOTP();


            EmailHelper emailSender = new EmailHelper(email, "Your OTP Code", "Your OTP is: " + generatedOTP);
            emailSender.send();


            Intent intent = new Intent(MainActivity.this, OTPVerificationActivity.class);
            intent.putExtra("otp_key", generatedOTP);
            startActivity(intent);

            Toast.makeText(this, "OTP sent to your email.", Toast.LENGTH_SHORT).show();
        });
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}