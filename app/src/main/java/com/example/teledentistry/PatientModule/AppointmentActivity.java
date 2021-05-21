package com.example.teledentistry.PatientModule;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.PatientModule.Adapters.Appointment_Slot_RecyclerView_Adapter;
import com.example.teledentistry.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class AppointmentActivity extends AppCompatActivity implements OnRadioButtonClickListener {
    private TextView mDisplayDate, starttime, endtime;
    private int mHour, mMinute;
    ImageButton cross;
    Button book;
    RecyclerView recyclerView;
    HashMap<String, List<String>> hashMap;
    View view;
    String date, numb;
    Appointment_Slot_RecyclerView_Adapter appointment_slot_recyclerView_adapter;
    int position;
    List<String> allSlotsOfGivenDate;
    String bookSlot, fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_pat_module);
        mDisplayDate = findViewById(R.id.date);
        Intent i = getIntent();

        hashMap = (HashMap<String, List<String>>) i.getSerializableExtra("slots");
        numb = i.getStringExtra("numb");
        fee = i.getStringExtra("fee");

        view = getLayoutInflater().inflate(R.layout.time_slots_doc_module, null, false);

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        builder.setSelection(today);
        builder.setCalendarConstraints(constraintsBuilder.build());


        final MaterialDatePicker materialDatePicker = builder.build();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "Date Picker");

            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                mDisplayDate.setText(materialDatePicker.getHeaderText());
                date = mDisplayDate.getText().toString();
                allSlotsOfGivenDate = hashMap.get(date);
                appointment_slot_recyclerView_adapter.setList(hashMap.get(date));
            }
        });

         appointment_slot_recyclerView_adapter = new
                Appointment_Slot_RecyclerView_Adapter(this, view, hashMap.get(date), date,this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(appointment_slot_recyclerView_adapter);

        starttime = findViewById(R.id.starttime);
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AppointmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                starttime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        endtime = findViewById(R.id.endtime);
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AppointmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                endtime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        book = findViewById(R.id.booked);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allSlotsOfGivenDate!=null){
                    bookSlot = allSlotsOfGivenDate.get(position);
                }
                Intent intent = new Intent(AppointmentActivity.this, ConfirmationAppoitnmentActivity.class);
                intent.putExtra("bookedSlot",bookSlot);
                intent.putExtra("date",date);
                intent.putExtra("fee",fee);
                intent.putExtra("numb",numb);

                startActivity(intent);

            }
        });


        cross = findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentActivity.this, SpecialistActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRadioClick(int position) {
        this.position = position;
    }
}