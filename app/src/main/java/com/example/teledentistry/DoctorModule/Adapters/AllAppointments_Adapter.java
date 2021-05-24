package com.example.teledentistry.DoctorModule.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.DoctorModule.AllAppointments_PatientsInfo_Activity;
import com.example.teledentistry.DoctorModule.BookedSlots_Model;
import com.example.teledentistry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AllAppointments_Adapter extends FirebaseRecyclerAdapter<BookedSlots_Model,AllAppointments_Adapter.ViewHolder> {
    Context context;
    List<String> list;
    String pat_id;
    String pat_name;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userId;

    List<Object> datalist;
    String data_of_list;
    String dataArray[];
    int count = 0;
    FirebaseRecyclerOptions<BookedSlots_Model> options;
    DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("Doctors");

    public AllAppointments_Adapter(Context context, FirebaseRecyclerOptions<BookedSlots_Model> options) {
        super(options);
        this.context = context;
//        this.list = list;
        this.pat_id = pat_id;
        this.options = options;

    }

    @NonNull
    @Override
    public AllAppointments_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View allAppointments_list = layoutInflater.inflate(R.layout.all_appointments_list_doc_module, parent, false);
        ViewHolder viewHolder = new ViewHolder(allAppointments_list);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(@NonNull AllAppointments_Adapter.ViewHolder holder, final int position) {
//
//    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final BookedSlots_Model model) {
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();

        String time = model.getTime();
        String time_split[] = time.split("-");
        String time1 = time_split[0];
        String time2 = time_split[1];

        holder.date_tv.setText(model.getDate());
        holder.name_tv.setText(model.getFull_name());
        holder.time_tv.setText(time1+" to");
        holder.time_tv2.setText(time2);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AllAppointments_PatientsInfo_Activity.class);
                i.putExtra("pat_id", model.getPat_id());
                v.getContext().startActivity(i);
            }
        });


//        reference.child(userId)
//                .child("booked slots")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String date = " ";
//                        String time = " ";
//
//                        List<HashMap> hashMapList;
//                        HashMap<String, Object> hashMap;
//
//                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                            hashMap = (HashMap<String, Object>) dataSnapshot.getValue();
//
////                            hashMap = hashMapList.get(position);
//
//                            Log.d("hashMap", String.valueOf(hashMap));
//                            Log.d("hashMap", hashMap.values()+":Values");
//
////                            Log.d("hashMappp", String.valueOf(hashMapList));
//
//                            datalist = (List<Object>) hashMap.values();
//                            Log.d("list", String.valueOf(datalist));
//                            date =(String) dataSnapshot.getKey();
//                            data_of_list = (String) datalist.get(0);
//
//                            Log.d("datta", String.valueOf(data_of_list));
//                            count++;
//                        }
//
//
//                        dataArray = data_of_list.split(";");
//                        time = (String) dataArray[0];
//                        pat_id =(String) dataArray[1];
//
//                        Log.d("actualData", date +","+ time +","+pat_id+"Inside");
//
//                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Patients").child(pat_id);
//                        final String finalDate = date;
//                        final String finalTime = time;
//
//                        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
//                            String pat_name;
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                pat_name = snapshot.child("full_name").getValue(String.class);
//                                Log.d("pat_name", pat_name);
//
//                                list.add(finalDate +";"+pat_name+";"+ finalTime);
//                                Log.d("list", String.valueOf(list));
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                            } });
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });



//         holder.allAppointments_date_tv.setText(date);
//        holder.allAppointments_patient_name_tv.setText(pat_name);
//        holder.allAppointments_patient_time_tv.setText(time);

//        String s = list.get(position);
//        String dataArray[];
//
//        dataArray = s.split(";");
//        String date = dataArray[0];
//        final String pat_name = dataArray[1];
//        String time = dataArray[2];
//        String time_split[] = time.split("-");
//        String time1 = time_split[0];
//        String time2 = time_split[1];

//        holder.date_tv.setText(date);
//        holder.name_tv.setText(pat_name);
//        holder.time_tv.setText(time1+" to");
//        holder.time_tv2.setText(time2);




    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date_tv, name_tv, time_tv,time_tv2;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_tv = (TextView) itemView.findViewById(R.id.date_tv);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
            time_tv = (TextView) itemView.findViewById(R.id.time_tv);
            time_tv2 = (TextView) itemView.findViewById(R.id.time_tv2);


            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }

    }
}
