package com.example.lyes.taquin;

import java.util.ArrayList;
import android.graphics.Color;
import android.app.AlertDialog;
import android.os.Bundle;
import java.util.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by LYES KHERBICHE on 29/02/2016.
 */
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    ArrayList<Integer> tab = new ArrayList<Integer>() ;
    ArrayList<Integer> tab2 = new ArrayList<Integer>() ;
    ArrayList<TextView>  valeurs= new ArrayList<TextView>();
    AlertDialog.Builder builder;
    Random rn = new Random();
    GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gd =new GestureDetector(getApplicationContext(),this);
        setContentView(R.layout.main_view);
        startGame();
    }
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent motionEvent) {}
    @Override
    public void onShowPress(MotionEvent motionEvent) {}
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        //droite
                        bouger(2);
                        Toast.makeText(getApplicationContext(),
                                "droite",Toast.LENGTH_SHORT).show();

                    } else {
                        // gauche
                        bouger(1);
                        Toast.makeText(getApplicationContext(),
                                "gauche",Toast.LENGTH_SHORT).show();
                    }
                }
                result = true;
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    //bas
                    bouger(4);
                    Toast.makeText(getApplicationContext(),
                            "bas",Toast.LENGTH_SHORT).show();
                } else {
                    //haut
                    bouger(3);
                    Toast.makeText(getApplicationContext(),
                            "haut",Toast.LENGTH_SHORT).show();
                }
            }
            result = true;
            testerSiGagner();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    protected void startGame(){
        int i,j;
        valeurs.add((TextView)findViewById(R.id.text1));
        valeurs.add((TextView)findViewById(R.id.text2));
        valeurs.add((TextView)findViewById(R.id.text3));
        valeurs.add((TextView)findViewById(R.id.text4));
        valeurs.add((TextView)findViewById(R.id.text5));
        valeurs.add((TextView)findViewById(R.id.text6));
        valeurs.add((TextView)findViewById(R.id.text7));
        valeurs.add((TextView)findViewById(R.id.text8));
        valeurs.add((TextView) findViewById(R.id.text9));
        for(i=0;i<=8;i++){
            j=i+1;
            tab.add(j);
            tab2.add(j);
            if(i != 8) {
                valeurs.get(i).setText(""+j);
            }
        }
        melanger();
    }

    /*les toast à impl*/
    private void bouger(int event){

        int index;
        TextView val;
        index = tab.indexOf(9); // l'index de la case qui contient le swipe

        if(event==1){ //deplac. une case à gauche
            Log.i("<--- ", "Essayer Deplacement d'une case à gauche");
            if(tab.get(2)!=9 && tab.get(5)!=9 && tab.get(8)!=9){ // le swipe n'est pas ds la 3eme colonne

                Log.i("....  ", "?  ?  x");
                Log.i("....  ", "?  ?  x");
                Log.i("....  ", "?  ?  x");

                valeurs.get(index).setText(tab.get(index + 1).toString());
                valeurs.get(index+1).setText("");
                valeurs.get(index).setBackgroundColor(Color.rgb(243, 62, 169));
                valeurs.get(index +1).setBackgroundColor(Color.rgb(38, 196, 236));

                tab.set(index, tab.get(index + 1));
                tab.set(index + 1, 9);
            }
        }
        if (event == 2) { //droite
            Log.i("---> ", "Essayer Deplacement d'une case à droite");
            if (tab.get(0) != 9 && tab.get(3) != 9 && tab.get(6) != 9) {

                Log.i("....  ", "x  ?  ?");
                Log.i("....  ", "x  ?  ?");
                Log.i("....  ", "x  ?  ?");

                valeurs.get(index).setText(tab.get(index - 1).toString());
                valeurs.get(index - 1).setText("");
                valeurs.get(index).setBackgroundColor(Color.rgb(243, 62, 169));
                valeurs.get(index - 1).setBackgroundColor(Color.rgb(38, 196, 236));

                tab.set(index, tab.get(index - 1));
                tab.set(index - 1, 9);
            }
        }
        if (event == 3) { //haut
            Log.i("^ ", "Essayer Deplacement d'une case vers le haut");
            if (tab.get(6) != 9 && tab.get(7) != 9 && tab.get(8) != 9) {

                Log.i("....  ", "?  ?  ?");
                Log.i("....  ", "?  ?  ?");
                Log.i("....  ", "x  x  x");

                valeurs.get(index).setText(tab.get(index + 3).toString());
                valeurs.get(index + 3).setText("");
                valeurs.get(index).setBackgroundColor(Color.rgb(243, 62, 169));
                valeurs.get(index + 3).setBackgroundColor(Color.rgb(38, 196, 236));

                tab.set(index, tab.get(index + 3));
                tab.set(index + 3, 9);
            }
        }
        if (event == 4){ //bas
            Log.i("! ", "Essayer Deplacement d'une case vers le bas");
            if ((tab.get(0)!= 9) && (tab.get(1) != 9) && (tab.get(2) != 9)) {

                Log.i("....  ", "x  x  x");
                Log.i("....  ", "?  ?  ?");
                Log.i("....  ", "?  ?  ?");

                valeurs.get(index).setText(tab.get(index - 3).toString());
                valeurs.get(index - 3).setText("");
                valeurs.get(index).setBackgroundColor(Color.rgb(243, 62, 169));
                valeurs.get(index - 3).setBackgroundColor(Color.rgb(38, 196, 236));

                tab.set(index, tab.get(index - 3));
                tab.set(index - 3, 9);
            }
        }

    }

    private void testerSiGagner () {
        if(tab.equals(tab2)){
            builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("BRAVO")
                    .setMessage(" Une Nouvelle Partie  ?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startGame();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Non", null);
            builder.show();
        }
    }

    private void melanger () {
        builder = new AlertDialog.Builder(this);
        builder
                .setTitle("GO")
                .setMessage(" Melanger ?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for (int i=0; i<70;i++) {
                            bouger(rn.nextInt(4) + 1);
                        }
                    }
                });
        builder.show();
    }
}

