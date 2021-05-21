package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teledentistry.PatientModule.FreeAppointmentActivty;
import com.example.teledentistry.PatientModule.ModelFreeConsultation;
import com.example.teledentistry.PatientModule.RequestedConsultationActivity;
import com.example.teledentistry.R;

import java.util.List;

public class FreeConsultationAdapter extends RecyclerView.Adapter<FreeConsultationAdapter.ViewHolder>{
    Context context;
    List<ModelFreeConsultation> myitems;

    public FreeConsultationAdapter(Context context, List<ModelFreeConsultation> myitems) {
        this.context = context;
        this.myitems = myitems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.freeconsultation_list_pat_module,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.d.setText(myitems.get(position).getDocname());
        holder.e.setText(myitems.get(position).getExper());
        holder.s.setText(myitems.get(position).getStatus());
        holder.sn.setText(myitems.get(position).getSoec());
        holder.st.setText(myitems.get(position).getSname());
        holder.f.setText(myitems.get(position).getFee());
        holder.sl.setText(myitems.get(position).getSlot());

    }

    @Override
    public int getItemCount() {
        return myitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView d,e,s,sn,st,f,sl;
        Button  b1,b2,b3,b4,b5,b6,b7,b8,b9;
        ImageView pp, favb;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            d=(TextView)itemView.findViewById(R.id.DocName_tv);
            e=(TextView)itemView.findViewById(R.id.experience_tv);
            s=(TextView)itemView.findViewById(R.id.status_tv);
            sn=(TextView)itemView.findViewById(R.id.textView18);
            st=(TextView)itemView.findViewById(R.id.textView19);
            f=(TextView)itemView.findViewById(R.id.textView16);
            sl=(TextView)itemView.findViewById(R.id.textView17);
            b1=(Button)itemView.findViewById(R.id.button2);
            b2=(Button)itemView.findViewById(R.id.button3);
            b3=(Button)itemView.findViewById(R.id.button4);
            b4=(Button)itemView.findViewById(R.id.button5);
            b5=(Button)itemView.findViewById(R.id.button6);
            b6=(Button)itemView.findViewById(R.id.button7);
            b7=(Button)itemView.findViewById(R.id.button8);
            b8=(Button)itemView.findViewById(R.id.button9);
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, RequestedConsultationActivity.class);
                    context.startActivity(intent);
                }
            });
            b9=(Button)itemView.findViewById(R.id.button10);
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, FreeAppointmentActivty.class);
                    context.startActivity(intent);
                }
            });
            pp=(ImageView)itemView.findViewById(R.id.imageView11);
            favb=(ImageView)itemView.findViewById(R.id.imageView8);

        }
    }




}
