package com.example.rpcosta.obligatorio1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;


public class DesafioIndividual extends Activity {
    private RelativeLayout rotar;
    private ImageView start;
    private RotateAnimation r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio_individual);
        rotar = (RelativeLayout) findViewById(R.id.relativeLayout1);
        start = (ImageView) findViewById(R.id.imageView9);
        final int[] posiciones ={360,440,235, 300, 110, 180};
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int degree =new Random().nextInt(posiciones.length);
                r = new RotateAnimation(0f, posiciones[degree], Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                r.setDuration(1600);
                r.setRepeatCount(0);
                r.setInterpolator(new DecelerateInterpolator());
                r.setFillAfter(true);
                r.setFillEnabled(true);
                rotar.startAnimation(r);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.desafio_individual, menu);
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
}
