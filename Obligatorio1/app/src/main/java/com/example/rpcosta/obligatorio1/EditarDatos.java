package com.example.rpcosta.obligatorio1;

import AsyncTask.BuscarJugador;
import AsyncTask.UploadPicture;
import DataBase.BaseDatos;
import Interfaces.BuscarJugadorInterface;
import Interfaces.UploadPicturesInterface;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class EditarDatos extends Activity implements UploadPicturesInterface, BuscarJugadorInterface {
    private Button changePass, changeNameEmail;
    private ImageView fotoPerfil;
    private static final int IMAGE_CAMERA = 1111;
    private static final int IMAGE_GALLERY = 2222;
    private SharedPreferences prefs;
    final String uploadFileName = "8888.jpg";
    private BaseDatos db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        changeNameEmail = (Button) findViewById(R.id.button);
        changePass = (Button) findViewById(R.id.button2);
        fotoPerfil = (ImageView) findViewById(R.id.imageView);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        db = new BaseDatos(this);
        Bundle b = getIntent().getExtras();
        final String id = b.getString("id");
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarDatos.this, OlvidoPass.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
        changeNameEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarDatos.this, ChangeNameMail.class);
                i.putExtra("id", id);
                startActivity(i);

            }
        });
        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        EditarDatos.this);

                // set title
                alertDialogBuilder.setTitle(getResources().getString(R.string.choose_camera));

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.camara), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, IMAGE_CAMERA);
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.galery), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(i, IMAGE_GALLERY);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
    }

    );

}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editar_datos, menu);
        return true;
    }
    

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CAMERA && resultCode == RESULT_OK
                && null != data) {
            
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + uploadFileName);
            try {
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);

                fo.write(bytes.toByteArray());
                fo.close();
                ArrayList<String> lista = new ArrayList<String>();
                lista.add(Environment.getExternalStorageDirectory()
                        + File.separator);
                lista.add(prefs.getString("id", null));
                lista.add(uploadFileName);
                new UploadPicture(EditarDatos.this).execute(lista);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == IMAGE_GALLERY && resultCode == RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + uploadFileName);

            try {
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);

                Bitmap image = decodeSampledBitmapFromResource(picturePath,50,50);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                fo.write(bytes.toByteArray());
                fo.close();
                ArrayList<String> lista = new ArrayList<String>();
                lista.add(Environment.getExternalStorageDirectory()
                        + File.separator);
                lista.add(prefs.getString("id", null));
                lista.add(uploadFileName);


                new UploadPicture(EditarDatos.this).execute(lista);

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(picturePath);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(EditarDatos.this, MiPerfil.class);
        startActivity(i);
    }

    @Override
    public void respuesta(String respuesta) {
          if(respuesta!=null){
              new BuscarJugador(EditarDatos.this).execute(prefs.getString("name", null));

          }
    }

    @Override
    public void buscarJugador(ArrayList<Jugador> listaJugadores) {
           if(listaJugadores!=null && !listaJugadores.isEmpty()){
               Boolean encontre = false;
               for(int i = 0; i<listaJugadores.size();i++){
                   if(listaJugadores.get(i).getId().equalsIgnoreCase(prefs.getString("id", null))&&!encontre){
                       db.insertarImagen(listaJugadores.get(i).getUrl());
                       fotoPerfil.setBackground(null);
                       Picasso.with(EditarDatos.this).load(listaJugadores.get(i).getUrl()).into(fotoPerfil);
                   }   
               }
           }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 2;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String filePath,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

}
