package com.example.rpcosta.ejercicio4.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.rpcosta.ejercicio4.Fragments.Fragment2;
import com.example.rpcosta.ejercicio4.Fragments.Fragment3;
import com.example.rpcosta.ejercicio4.Interfaces.InterfaceFragment1;
import com.example.rpcosta.ejercicio4.R;

public class MyActivity2 extends Activity implements InterfaceFragment1 {
    private Fragment2 f2;
    private Fragment3 f3;
    int frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);
        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            String q = b.getString("query");
            b.putString("query", q);
            f2 = new Fragment2();
            f2.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.contenedor, f2).commit();
        }
        else{
            frag = 3;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);

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
    public void AvisoClick(String item, int fgmt) {
        frag = fgmt;
        f3 = new Fragment3();
        getFragmentManager().beginTransaction().replace(R.id.contenedor, f3).commit();
        Bundle b = new Bundle();
        b.putString("item", item);
        f3.setArguments(b);


    }

    @Override
    public void onBackPressed() {
        if (frag == 3) {
            if (f2 == null) {
                f2 = new Fragment2();
            }
            Bundle b = getIntent().getExtras();
            String q = b.getString("query");
            b.putString("query", q);
            f2.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.contenedor, f2).commit();
        }
    }
}

