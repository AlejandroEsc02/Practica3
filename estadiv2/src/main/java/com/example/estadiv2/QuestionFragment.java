package com.example.estadiv2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;



public class QuestionFragment extends Fragment {
    private EditText epregunta;
    private Button bsalir, bpreguntar;
    private FragmentManager fm;
    private FragmentTransaction ft;


    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        epregunta = view.findViewById(R.id.epregunta);
        bsalir = view.findViewById(R.id.bsalir);
        bpreguntar = view.findViewById(R.id.bpreguntar);

        fm = getFragmentManager();
        ft = fm.beginTransaction();


        bpreguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pregunta;
                if (epregunta.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite todos los campos.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    pregunta = epregunta.getText().toString();
                    PreguntasFragment preguntasFragment = new PreguntasFragment();
                    ft.replace(R.id.frame, preguntasFragment).commit();
                }


            }
        });

        bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreguntasFragment preguntasFragment = new PreguntasFragment();
                ft.replace(R.id.frame, preguntasFragment).commit();

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
