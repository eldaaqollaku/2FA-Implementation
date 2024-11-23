package com.example.detyra_2fa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OTPVerificationActivity extends AppCompatActivity {

    private EditText OTPinput;
    private String OTPoriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        OTPinput = findViewById(R.id.otp_field);
        Button buttonVerify = findViewById(R.id.verify_button);


        OTPoriginal = getIntent().getStringExtra("otp_key");
        Log.d("OTPActivity", "Received OTP: " + OTPoriginal);

        buttonVerify.setOnClickListener(v -> {
            String enteredOTP = OTPinput.getText().toString().trim();

            if (enteredOTP.equals(OTPoriginal)) {

                Intent intent = new Intent(OTPVerificationActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {

                Toast.makeText(this, "Incorrect code.Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}