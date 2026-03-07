package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etPhone;
    RadioGroup radioGroup;
    Spinner spinner;
    Button btnDate, btnSubmit;
    String selectedDate = "";      

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Patient App - Register No: 23ITR046");
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPhone = findViewById(R.id.etPhone);
        radioGroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.spinnerIllness);
        btnDate = findViewById(R.id.btnDate);
        btnSubmit = findViewById(R.id.btnSubmit);

        String[] illness = {"Fever", "Cold", "Diabetes", "Heart Problem"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, illness);
        spinner.setAdapter(adapter);

        btnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(MainActivity.this,
                    (view, year, month, day) ->
                            selectedDate = day + "/" + (month+1) + "/" + year,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String phone = etPhone.getText().toString();

            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            String gender = radioButton.getText().toString();

            String illnessType = spinner.getSelectedItem().toString();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            intent.putExtra("phone", phone);
            intent.putExtra("gender", gender);
            intent.putExtra("illness", illnessType);
            intent.putExtra("date", selectedDate);
            startActivity(intent);
        });
    }
}