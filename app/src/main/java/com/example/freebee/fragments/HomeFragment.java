package com.example.freebee.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.freebee.ApiClient;
import com.example.freebee.R;
import com.example.freebee.adapter.PackagesAdapter;
import com.example.freebee.models.CallRates.Packages;

import org.abtollc.sdk.AbtoApplication;
import org.abtollc.sdk.AbtoPhone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    View root;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String username,password;
    AbtoPhone abtoPhone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_home, container, false);
        progressBar=root.findViewById(R.id.progressBar);
        recyclerView=root.findViewById(R.id.recyclerView);

        abtoPhone = ((AbtoApplication) getActivity().getApplication()).getAbtoPhone();
        int accId = (int)abtoPhone.getCurrentAccountId();
        username=abtoPhone.getConfig().getAccount(accId).username;
        password=abtoPhone.getConfig().getAccount(accId).getPassword();
        setRecyclerView();

        ApiClient.getInstance().getPackages(username,password,"NULL")
                .enqueue(new Callback<Packages>() {
                    @Override
                    public void onResponse(Call<Packages> call, Response<Packages> response) {
                        if(response.isSuccessful()){
                            PackagesAdapter adapter=new PackagesAdapter(root.getContext(),response.body().getData());
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Packages> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
        return root;
    }

    private void setRecyclerView(){
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(),LinearLayoutManager.VERTICAL));
    }
}