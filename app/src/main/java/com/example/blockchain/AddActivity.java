package com.example.blockchain;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.blockchain.R;

import java.util.Calendar;

public class AddActivity extends Activity {

    EditText data;
    EditText date;
    Button addContact;
    String dateToPrint;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        data = findViewById(R.id.data);
        date = findViewById(R.id.date);
        addContact = findViewById(R.id.addContacts);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempData = data.getText().toString();
                String tempDate = date.getText().toString();

                String Result = tempData + "," + tempDate;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("result",Result);
                setResult(RESULT_OK,resultIntent);
                finish();

                //String result =
            }
        });
    }
    public void onClickDate1(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                dateToPrint = dayOfMonth + "/" + month + "/" + year;
                //birthDate = dayOfMonth;
                //birthMonth = month;
                //birthYear = year;
                date.setText(dateToPrint);
            }
        };
    }
}
