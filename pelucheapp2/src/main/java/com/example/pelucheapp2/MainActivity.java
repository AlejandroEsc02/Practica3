package com.example.pelucheapp2;

import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDataPass {
    private ArrayList<Peluchito> peluches = new ArrayList<Peluchito>();
    private String inv;
    private String encontrado;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    InventarioFragment tab0 = new InventarioFragment();
    AgregarFragment tab1 = new AgregarFragment();
    BuscarFragment tab2 = new BuscarFragment();
    EliminarFragment tab3 = new EliminarFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Peluchito pe = new Peluchito("", 0, 0, 0);
        peluches.add(pe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    inv = "";
                    for (int i = 1; i < peluches.size(); i++) {
                        inv = inv + "Nombre: " + peluches.get(i).getNombre().toString() + "\n" +
                                "Id: " + String.valueOf(peluches.get(i).getId()) + "\n" +
                                "Unidades Disp: " + String.valueOf(peluches.get(i).getCantidad()) + "\n" +
                                "Precio: " + String.valueOf(peluches.get(i).getPrecio() + "\n" + "______" + "\n");
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("message", inv);
                    tab0.setArguments(bundle);

                    return tab0;
                case 1:

                    return tab1;
                case 2:
                    encontrado="";
                    Bundle bunde = new Bundle();
                    bunde.putString("encontrado", encontrado);
                    tab2.setArguments(bunde);

                    return tab2;
                case 3:

                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void agrega(String nombre, int id, int cantidad, double precio) {
        int f=1;
        for (int i = 1; i < peluches.size(); i++) {
            if (nombre.equals(peluches.get(i).getNombre().toString())) {
                Toast.makeText(this,  nombre + " Ya existe", Toast
                        .LENGTH_LONG).show();
                f = 2;
                break;
            }

        }if (f==1) {
            Peluchito pe = new Peluchito(nombre, cantidad, id, precio);
            peluches.add(pe);
            actualizarinv();
            Toast.makeText(this, nombre + " Agregado",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void busca(String nombre) {
        encontrado="";
        int f=1;
        for (int i = 1; i < peluches.size(); i++) {
            if (nombre.equals(peluches.get(i).getNombre().toString())) {
                encontrado = "Nombre: " + peluches.get(i).getNombre().toString() + "\n" +
                        "Id: " + String.valueOf(peluches.get(i).getId()) + "\n" +
                        "Unidades Disp: " + String.valueOf(peluches.get(i).getCantidad()) + "\n" +
                        "Precio: " + String.valueOf(peluches.get(i).getPrecio());

                Bundle bundle = new Bundle();
                bundle.putString("encontrado", encontrado);
                tab2.setArguments(bundle);
                f=2;
                break;
            }
        }if (f==1) {Toast.makeText(this,"Peluche "+nombre+" No existe", Toast
                .LENGTH_LONG).show();}
    }

    @Override
    public void elimina(String nombre) {
        int f = 1;
        for (int i = 0; i < peluches.size(); i++) {
            if (nombre.equals(peluches.get(i).getNombre().toString())) {
                peluches.remove(peluches.get(i));
                f = 2;
                actualizarinv();
                break;
            }
        }
        if (f == 2) {
            Toast.makeText(this, "Peluche " + nombre + " Eliminado", Toast
                    .LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Peluche " + nombre + " No existe", Toast
                    .LENGTH_LONG).show();

        }
    }
    void actualizarinv(){
        inv = "";
        for (int i = 1; i < peluches.size(); i++) {
            inv = inv + "Nombre: " + peluches.get(i).getNombre().toString() + "\n" +
                    "Id: " + String.valueOf(peluches.get(i).getId()) + "\n" +
                    "Unidades Disp: " + String.valueOf(peluches.get(i).getCantidad()) + "\n" +
                    "Precio: " + String.valueOf(peluches.get(i).getPrecio() + "\n" + "______" + "\n");
        }

        Bundle bundle = new Bundle();
        bundle.putString("message", inv);
        tab0.setArguments(bundle);
    }
}

