package com.example.freebee;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.juanpabloprado.countrypicker.CountryPicker;
import com.juanpabloprado.countrypicker.CountryPickerListener;

public class CountryDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root=inflater.inflate(R.layout.dialog_country,container,false);
        CountryPicker picker = CountryPicker.getInstance("Select Country", new CountryPickerListener() {
            @Override public void onSelectCountry(String name, String code) {
                Toast.makeText(getActivity(), "Name: " + name, Toast.LENGTH_SHORT).show();
                DialogFragment dialogFragment =
                        (DialogFragment) getFragmentManager().findFragmentByTag("CountryPicker");
                dialogFragment.dismiss();
                dismiss();
            }
        });
        picker.show(getFragmentManager(), "CountryPicker");
        /*getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        CountryPicker.getInstance(new CountryPickerListener() {
                            @Override
                            public void onSelectCountry(String name, String code) {
                                Toast.makeText(getActivity(), "Name: " + name, Toast.LENGTH_SHORT).show();
                            }
                        }))
                .commit();*/
        return root;
    }
}
