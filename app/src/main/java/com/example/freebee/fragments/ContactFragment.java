package com.example.freebee.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.freebee.R;
import com.example.freebee.adapter.AllContactsAdapter;
import com.example.freebee.helper.Utils;
import com.example.freebee.models.Contact.ContactVO;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {
    private View root;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private AllContactsAdapter contactAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);
        progressBar=root.findViewById(R.id.progressBar);
        new AsyncContacts().execute();
        return root;
    }



    public class AsyncContacts extends AsyncTask<Void,Void,List<ContactVO>>{

        @Override
        protected List<ContactVO> doInBackground(Void... voids) {
            return Utils.getAllContacts(getContext());
        }

        @Override
        protected void onPostExecute(List<ContactVO> contactVOS) {
            super.onPostExecute(contactVOS);
            if(contactVOS!=null && contactVOS.size()>0){

                contactAdapter = new AllContactsAdapter(contactVOS, getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(contactAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }
}