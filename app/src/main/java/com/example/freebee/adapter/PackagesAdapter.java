package com.example.freebee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freebee.R;
import com.example.freebee.models.CallRates.Datum;

import java.util.List;
import java.util.zip.Inflater;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.PackagesViewHolder> {
    Context context;
    List<Datum> list;
    LayoutInflater inflater;

    public PackagesAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PackagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_row_packages,null);
        return new PackagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackagesViewHolder holder, int position) {
        if(list!=null){
            Datum datum=list.get(position);
            holder.txt_package.setText(datum.getPackagename());
            if(datum.getDialylimit()!=null) {
                holder.txt_daily.setText(datum.getDialylimit().toString()+" "+datum.getPackagetype());
            }
            if(datum.getValidity()!=null) {
                holder.txt_validity.setText(datum.getValidity().toString());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PackagesViewHolder extends RecyclerView.ViewHolder{
        TextView txt_package;
        TextView txt_validity;
        TextView txt_daily;
        public PackagesViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_package=itemView.findViewById(R.id.txt_package);
            txt_validity=itemView.findViewById(R.id.txt_validity);
            txt_daily=itemView.findViewById(R.id.txt_daily);
        }
    }
}
