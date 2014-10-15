package com.example.rpcosta.ejercicio4android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity implements ActionBar.TabListener{
    private ActionBar bar;
    private Comidas c;
    private Postres p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        c = new Comidas();
        c.setContext(this);
        p = new Postres();
        p.setContext(this);
        bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = bar.newTab();
        ActionBar.Tab tab2= bar.newTab();

        tab1.setIcon(R.drawable.food);
        tab2.setIcon(R.drawable.postre);
        tab1.setTabListener(this);
        tab2.setTabListener(this);

        bar.addTab(tab1);
        bar.addTab(tab2);

        getFragmentManager().beginTransaction().remove(c).add(R.id.linearDesino,c).commit();

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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if(tab.getPosition()==0){
                getFragmentManager().beginTransaction().remove(p).add(R.id.linearDesino,c).commit();

            }
        else{
                getFragmentManager().beginTransaction().remove(c).add(R.id.linearDesino,p).commit();
            }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
