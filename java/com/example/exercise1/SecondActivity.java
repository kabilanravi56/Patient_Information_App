package com.example.exercise1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Patient Details - 23ITR046");
        setContentView(R.layout.activity_second);

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnSMS = findViewById(R.id.btnSMS);
        Button btnEmail = findViewById(R.id.btnEmail);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");

        tvDetails.setText("Patient Details:\n\n" +
                "Name: " + intent.getStringExtra("name") + "\n" +
                "Age: " + intent.getStringExtra("age") + "\n" +
                "Gender: " + intent.getStringExtra("gender") + "\n" +
                "Illness: " + intent.getStringExtra("illness") + "\n" +
                "Date: " + intent.getStringExtra("date"));

        btnCall.setOnClickListener(v -> showDialog("call", phone));
        btnSMS.setOnClickListener(v -> showDialog("sms", phone));
        btnEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"doctor@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Patient Appointment");
            startActivity(emailIntent);
        });
    }

    private void showDialog(String type, String phone) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    if (type.equals("call")) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL,
                                Uri.parse("tel:" + phone));
                        startActivity(callIntent);
                    } else {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("sms:" + phone));
                        startActivity(smsIntent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}