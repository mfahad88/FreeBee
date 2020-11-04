package com.example.freebee.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freebee.R;
import com.example.freebee.models.Contact.ContactVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllContactsAdapter extends RecyclerView.Adapter implements Filterable {

    private List<ContactVO> contactVOList;
    private Context mContext;
    private List<ContactVO> contactVOListAll;
    public AllContactsAdapter(List<ContactVO> contactVOList, Context mContext){
        this.contactVOList = contactVOList;
        this.mContext = mContext;
        this.contactVOListAll=new ArrayList<>(contactVOList);
    }



    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_row_contact, null);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder viewHolder=(ContactViewHolder)holder;
        ContactVO contactVO = contactVOList.get(position);
        viewHolder.tvContactName.setText(contactVO.getContactName());
        viewHolder.tvPhoneNumber.setText(contactVO.getContactNumber());
        if(position>0){
            int previous=holder.getPosition()-1;
            System.out.println(previous+". "+contactVOList.get(previous).getContactName().substring(0,1)+"\t"+position+". "+contactVOList.get(position).getContactName().substring(0,1)+"\t"+
                    String.valueOf(contactVOList.get(previous).getContactName().charAt(0)!=contactVOList.get(position).getContactName().charAt(0)));
            /*if(contactVOList.get(previous).getContactName().charAt(0)!=contactVOList.get(position).getContactName().charAt(0)){
                viewHolder.txt_alphabet.setText(String.valueOf(contactVOList.get(position).getContactName().charAt(0)));
                viewHolder.txt_alphabet.setVisibility(View.VISIBLE);
            }*/
        }

        try{


            if(contactVOList.get(position).getContactImage()!=null) {
                Bitmap bp= MediaStore.Images.Media
                                .getBitmap(mContext.getContentResolver(),
                                        Uri.parse(contactVOList.get(holder.getAdapterPosition()).getContactImage()));
                Glide.with(viewHolder.itemView.getContext()).load(bp).circleCrop()
                        .into(viewHolder.ivContactImage);


            }else{
                Glide.with(viewHolder.itemView.getContext()).load(R.drawable.ic_baseline_person_24).circleCrop()
                        .into(viewHolder.ivContactImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public int getItemCount() {
        return contactVOList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ContactVO> filterList=new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filterList.addAll(contactVOListAll);
            }else {
                for(ContactVO vo:contactVOListAll){
                    if(vo.getContactName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(vo);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            contactVOList.clear();
            contactVOList.addAll((List<ContactVO>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView ivContactImage;
        TextView tvContactName;
        TextView tvPhoneNumber;
        TextView txt_alphabet;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ivContactImage = (ImageView) itemView.findViewById(R.id.ivContactImage);
            tvContactName = (TextView) itemView.findViewById(R.id.tvContactName);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
//            txt_alphabet=itemView.findViewById(R.id.txt_alphabet);
        }
    }
}