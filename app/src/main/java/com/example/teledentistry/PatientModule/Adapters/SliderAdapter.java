package com.example.teledentistry.PatientModule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.teledentistry.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    int[] images;
    Context context;
    public SliderAdapter(int[] images){

this.images=images;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_pat_module,parent,false);

        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
viewHolder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getCount() {


        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        public Holder(final View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);

        }



    }

}
