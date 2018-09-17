package com.example.pelucheapp2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EliminarFragment extends Fragment {
    private EditText enombre;
    private Button beliminar;

    OnDataPass dataPasser;

    public EliminarFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_eliminar, container, false);

        enombre=view.findViewById(R.id.enombre);
        beliminar=view.findViewById(R.id.beliminar);

        enombre.setText("");

        beliminar.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (enombre.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite todos los campos",
                            Toast.LENGTH_SHORT).show();

                }else {
                    dataPasser.elimina(enombre.getText().toString());
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataPasser = (OnDataPass) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnDataPass");
        }
    }
}
