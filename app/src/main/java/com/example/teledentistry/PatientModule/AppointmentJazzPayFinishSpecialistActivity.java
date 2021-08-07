package com.example.teledentistry.PatientModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teledentistry.DoctorModule.BookedSlots_Model;
import com.example.teledentistry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class AppointmentJazzPayFinishSpecialistActivity extends AppCompatActivity {

    Button finish;
    String date, bookedSlot, bookingFor, reasonFor, fee, card_number, mobile_number, cvv, expire_date, numb, cnic;
    List<String> allSlots;
    List<String> allBookedSlots;
    final HashMap<String, Object> postHashMap = new HashMap<>();
    String doc_id = "";
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;
    String pat_name;
    String imageUrl;
    String clicked;
    String clicked2;
    BookedSlots_Model bookedSlots_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentpay_finish_specialist_pat_module);
        finish = findViewById(R.id.finish_btnn);

        Intent i = getIntent();
        date = i.getStringExtra("date");
        bookedSlot = i.getStringExtra("bookedSlot");
        bookingFor = i.getStringExtra("bookingFor");
        reasonFor = i.getStringExtra("reasonFor");
        fee = i.getStringExtra("fee");
        mobile_number = i.getStringExtra("mobile_number");
        numb = i.getStringExtra("doc_numb");
        cnic = i.getStringExtra("cnic");
        //clicked = i.getStringExtra("clicked");
        clicked2 = i.getStringExtra("clicked2");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettingListOfBookedSlots();

                Intent i = new Intent(AppointmentJazzPayFinishSpecialistActivity.this, JazzCashPaymentActivity.class);
                //startActivity(new Intent(MainActivity.this, PaymentActivity.class));
                i.putExtra("fee", fee);

                startActivityForResult(i, 0);

               // openDialog();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0 && resultCode == RESULT_OK) {
            // Get String data from Intent
            String ResponseCode = data.getStringExtra("pp_ResponseCode");
            System.out.println("DateFn: ResponseCode:" + ResponseCode);
            if (ResponseCode.equals("000")) {
                Toast.makeText(getApplicationContext(), "Payment Success", Toast.LENGTH_SHORT).show();

                final Query reference1;
                reference1 = FirebaseDatabase.getInstance().getReference("Doctors").orderByChild("phone_no").equalTo(numb);
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        firebaseAuth = FirebaseAuth.getInstance();
                        user = firebaseAuth.getCurrentUser();
                        userId = user.getUid();

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            doc_id = snapshot1.getKey();
                        }
                        //Transection
                        DatabaseReference database_Reference = FirebaseDatabase.getInstance().getReference("Transaction").push();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("doc_id", doc_id);
                        hashMap.put("pat_id", userId);
                        hashMap.put("disease", reasonFor);
                        hashMap.put("fee", fee);
                        hashMap.put("date", date);
                        database_Reference.setValue(hashMap);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }




    // Doctors --> booked Slots date, slot, pat_id
    //Patient --> booked slots date slot, doc_id
    //Transection --> remaining data


    private List<String> gettingListOfBookedSlots() {
        final Query reference1;
        reference1 = FirebaseDatabase.getInstance().getReference("Doctors").orderByChild("phone_no").equalTo(numb);

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> postHashMap = new HashMap<>();
                firebaseAuth = FirebaseAuth.getInstance();
                user = firebaseAuth.getCurrentUser();
                userId = user.getUid();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    postHashMap = (HashMap<String, Object>) snapshot1.child("slots").getValue();
                    doc_id = snapshot1.getKey();
                }

                allSlots = (List<String>) postHashMap.get(date);

                if (allSlots.contains(bookedSlot)) {

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctors").child(doc_id);

                    final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance()
                            .getReference("Doctors").child(doc_id).child("booked slots").push();

                    DatabaseReference pat_reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);
                    pat_reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            pat_name = snapshot.child("full_name").getValue(String.class);
                            imageUrl = snapshot.child("imageUrl").getValue(String.class);
                            bookedSlots_model = new BookedSlots_Model(date, pat_name, bookedSlot, userId, imageUrl, bookingFor, reasonFor);
                            databaseReference1.setValue(bookedSlots_model);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


//                    HashMap<String,Object> hashMap = new HashMap<>();

                    List<String> stringList = (List<String>) postHashMap.get(date);
                    assert stringList != null;
                    stringList.remove(bookedSlot);
                    postHashMap.put(date, stringList);



                    HashMap<String, Object> hashMap1 = new HashMap<>();
//                    hashMap1.put("booked slots", hashMap);

                    hashMap1.put("slots", postHashMap);

                    databaseReference.updateChildren(hashMap1).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AppointmentJazzPayFinishSpecialistActivity.this, "Data sat on firebase", Toast.LENGTH_SHORT).show();
                        }
                    });


                    //Patient
                    final DatabaseReference patient_reference, patient_reference1;
                    patient_reference = FirebaseDatabase.getInstance().getReference("Patients").child(userId);
                    patient_reference1 = FirebaseDatabase.getInstance().getReference("Patients")
                            .child(userId).child("booked slots").push();

                    BookedSlots_Model bookedSlots_model1 = new BookedSlots_Model(date, doc_id, bookedSlot, bookingFor, reasonFor);
                    patient_reference1.setValue(bookedSlots_model1);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return allSlots;
    }

    public void openDialog() {
        OpenDialogAppintmentSpecialistclass openDialogAppintmentSpecialistclassn = new OpenDialogAppintmentSpecialistclass(bookedSlot, getApplicationContext());
        openDialogAppintmentSpecialistclassn.show(getSupportFragmentManager(), "open_dialog_appointment_specialist_class");
    }
}