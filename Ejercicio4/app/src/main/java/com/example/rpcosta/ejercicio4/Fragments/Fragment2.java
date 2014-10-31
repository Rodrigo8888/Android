package com.example.rpcosta.ejercicio4.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.example.rpcosta.ejercicio4.Asynctask.Busqueda;
import com.example.rpcosta.ejercicio4.Dominio.AdapterList;
import com.example.rpcosta.ejercicio4.Dominio.Item;
import com.example.rpcosta.ejercicio4.Interfaces.Datos;
import com.example.rpcosta.ejercicio4.Interfaces.InterfaceFragment1;
import com.example.rpcosta.ejercicio4.R;

import java.util.ArrayList;

/**
 * Created by rpcosta on 24/10/14.
 */
public class Fragment2 extends Fragment implements Datos {
    private InterfaceFragment1 context;
    private AdapterList adapter;
    private ArrayList<Item> lista;
    private ListView miLista;
    private String query;
    private int offset;
    private static final int max = 15;
    private String paginas = "&limit=15";
    private static String listQuery = "Lista";
    private String url;
    //ProgressDialog dialogo = null;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            context = (InterfaceFragment1) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement InterfaceFragment1");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Bundle b = getArguments();
        String querys = b.getString("query");
        query=querys;
        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        offset = 0;
        url = "https://api.mercadolibre.com/sites/MLU/search?q=";
        lista = new ArrayList<Item>();
        miLista = (ListView) rootView.findViewById(R.id.listView1);
        if (savedInstanceState != null) {
            EditText texto = (EditText) rootView.findViewById(R.id.editText1);
            miLista = (ListView) rootView.findViewById(R.id.listView1);
            lista = (ArrayList<Item>) savedInstanceState.getSerializable(listQuery);
            adapter = new AdapterList(getActivity(), R.id.listView1, lista);
            if (miLista.getAdapter() == null) {
                miLista.setAdapter(adapter);
            }

        } else {
            lista = new ArrayList<Item>();
            adapter = new AdapterList(getActivity(), R.id.listView1, lista);
            url += query + paginas + "&offset=" + offset;
            miLista = (ListView) rootView.findViewById(R.id.listView1);
            if (miLista.getAdapter() == null) {
                miLista.setAdapter(adapter);
                //dialogo = ProgressDialog.show(getActivity(), "", "Buscando...", true);
            }

            new Busqueda(Fragment2.this).execute(url);
        }
        miLista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= offset + max) {
                    //dialogo = ProgressDialog.show(getActivity(), "", "Buscando...", true);
                    url = "https://api.mercadolibre.com/sites/MLU/search?q=";
                    offset += max;
                    if (miLista.getAdapter() == null) {
                        miLista.setAdapter(adapter);
                    }
                    url += query + paginas + "&offset=" + offset;
                    new Busqueda(Fragment2.this).execute(url);
                }
            }
        });

        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                context.AvisoClick(lista.get(position),3);
            }
        });


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(listQuery, lista);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void refreshList(ArrayList<Item> items) {
        //dialogo.dismiss();
        if (lista != null) {
            lista.addAll(items);
        } else {
            lista = items;
        }
        if (miLista.getAdapter() == null) {
            miLista.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }
}
