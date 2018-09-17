package com.example.pelucheapp2;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class InventarioFragment extends Fragment {
    private TextView infop;
    private Button actualizar;

    public InventarioFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventario, container, false);
        infop = view.findViewById(R.id.infop);
        actualizar = view.findViewById(R.id.actualizar);
        String inv = this.getArguments().getString("message");
        infop.setText(inv);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                reload();
            }

        });
        return view;
    }
    void reload(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}