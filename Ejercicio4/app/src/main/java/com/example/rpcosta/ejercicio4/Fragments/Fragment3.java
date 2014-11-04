package com.example.rpcosta.ejercicio4.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rpcosta.ejercicio4.Activitys.DescripcionItem;
import com.example.rpcosta.ejercicio4.Asynctask.Descripcion;
import com.example.rpcosta.ejercicio4.Asynctask.ItemDescription;
import com.example.rpcosta.ejercicio4.Asynctask.ManejadorImagenes;
import com.example.rpcosta.ejercicio4.DataBase.DBHelper;
import com.example.rpcosta.ejercicio4.Dominio.Item;
import com.example.rpcosta.ejercicio4.Interfaces.DatosItems;
import com.example.rpcosta.ejercicio4.Interfaces.InterfaceFragment1;
import com.example.rpcosta.ejercicio4.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by rpcosta on 24/10/14.
 */
public class Fragment3 extends Fragment implements DatosItems {
    private Item i;
    private String url;
    private ImageView imagen;
    private View rootView;
    private TextView titulo, subtitulo, precio, cantidad;
    private ManejadorImagenes manejador;
    private ImageView fav;
    private InterfaceFragment1 miCtx;
    private Boolean transparence = false;
    private ArrayList<Item> favUser;
    private Button descripcion;
    private Item item;
    Dao dao;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            miCtx = (InterfaceFragment1) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement InterfaceFragment1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Bundle b = getArguments();
        final Item itemS = (Item) b.getSerializable("ItemS");
        item = (Item) b.getSerializable("item");
        final DBHelper helper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);
        rootView = inflater.inflate(R.layout.fragment3, container, false);
        descripcion = (Button) rootView.findViewById(R.id.button2);
        fav = (ImageView) rootView.findViewById(R.id.imageView3);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        if (itemS == null) {
            try {
                dao = helper.getItemDao();
                favUser = (ArrayList<Item>) dao.queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (favUser != null) {
                for (int i = 0; (i < favUser.size()) && (!transparence); i++) {
                    if (favUser.get(i).getId().equalsIgnoreCase(item.getId())) {
                        transparence = true;
                    }
                }
            }
            if (transparence) {
                fav.getBackground().setAlpha(255);
            } else {
                fav.getBackground().setAlpha(50);
            }

            manejador = ManejadorImagenes.getInstance();
            url = "https://api.mercadolibre.com/items/";
            url += item.getId();
            new ItemDescription(this).execute(url);
        } else {
            item = itemS;
            manejador = ManejadorImagenes.getInstance();
            url = "https://api.mercadolibre.com/items/";
            url += item.getId();
            new ItemDescription(this).execute(url);
        }
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!transparence) {
                        fav.getBackground().setAlpha(255);
                        dao.create(item);
                        Toast msj = Toast.makeText(getActivity(), "El ítem fue agregado a Favoritos", Toast.LENGTH_SHORT);
                        msj.show();
                        transparence = true;
                    } else {
                        DeleteBuilder<Item, Integer> deleteBuilder = dao.deleteBuilder();
                        deleteBuilder.where().eq("id", item.getId());
                        deleteBuilder.delete();
                        fav.getBackground().setAlpha(50);
                        Toast msj = Toast.makeText(getActivity(), "El ítem fue eliminado de Favoritos", Toast.LENGTH_SHORT);
                        msj.show();
                        transparence = false;

                    }
                } catch (SQLException e) {
                    Log.e("Error", "Error guardando favorito");
                }
            }
        });
        descripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlDes = "https://api.mercadolibre.com/items/";
                urlDes += item.getId() + "/description";
                new Descripcion(Fragment3.this).execute(urlDes);
            }
        });
        return rootView;
    }


    @Override
    public void refreshdatos(Item item) {
        titulo = (TextView) rootView.findViewById(R.id.textView5);
        subtitulo = (TextView) rootView.findViewById(R.id.textView6);
        precio = (TextView) rootView.findViewById(R.id.textView7);
        cantidad = (TextView) rootView.findViewById(R.id.textView8);
        imagen = (ImageView) rootView.findViewById(R.id.imageView);
        i = item;
        titulo.setText(i.getTitle());
        if (i.getSubtitle() == null) {
            subtitulo.setText("Sin subtitulo");
        } else {
            subtitulo.setText(i.getSubtitle());
        }
        precio.setText("$" + i.getPrice());
        cantidad.setText("Cantidad: " + i.getAvailable_quantity());
        //Verificar antes si la lista es o no vacia
        if (i.getLista() != null) {
            manejador.setImagenes(i.getLista().get(0).getUrl(), imagen);
        } else {
            manejador.setImagenes(i.getThumbnail(), imagen);
        }
    }

    @Override
    public void refreshDescripcion(String desc) {
        if(desc !=null) {
            Intent i = new Intent(getActivity(), DescripcionItem.class);
            i.putExtra("descripcion", desc);
            startActivity(i);
        }
    }


}
