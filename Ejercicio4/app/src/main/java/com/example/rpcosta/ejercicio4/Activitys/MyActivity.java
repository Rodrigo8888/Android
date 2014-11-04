package com.example.rpcosta.ejercicio4.Activitys;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rpcosta.ejercicio4.DataBase.DBHelper;
import com.example.rpcosta.ejercicio4.R;
import com.example.rpcosta.ejercicio4.Services.MyService;
import com.j256.ormlite.android.apptools.OpenHelperManager;


public class MyActivity extends Activity {
    private Button buscar;
    private EditText query;
    private static String Cquery = "query";
    private Boolean isnumeric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        DBHelper helper = OpenHelperManager.getHelper(this, DBHelper.class);
        helper.createDB();
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        /*Map<String,?> keys = prefs.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
                    entry.getValue().toString());
        }*/
        String busqueda = prefs.getString(Cquery, null);
        query = (EditText) findViewById(R.id.editText1);
        if (busqueda != null) {
            String search = busqueda.replace("%20", " ");
            query.setText(search);
        }
        buscar = (Button) findViewById(R.id.button1);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isnumeric = true;
                try {
                    int num = Integer.parseInt(query.getText().toString());
                } catch (NumberFormatException e) {
                    isnumeric = false;

                }
                if (query.getText().toString().isEmpty() || isnumeric) {
                    if (isnumeric) {
                        Toast msj = Toast.makeText(MyActivity.this, "La busqueda no puede ser numérica", Toast.LENGTH_SHORT);
                        msj.show();
                    } else {
                        Toast msj = Toast.makeText(MyActivity.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT);
                        msj.show();
                    }
                } else {
                    String busqueda = query.getText().toString();
                    String input = busqueda.replace(" ", "%20");
                    editor.putString(Cquery, input);
                    editor.commit();
                    Intent i = new Intent(MyActivity.this, MyActivity2.class);
                    i.putExtra(Cquery, input);
                    startActivity(i);

                }
            }
        });
        if (!isMyServiceRunning()) {
            Intent intent=new Intent(MyActivity.this,MyService.class);
            startService(intent);
        }

    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.example.rpcosta.ejercicio4.Services.MyService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
