package com.vazk.calculator.menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.InterstitialAd;
import com.vazk.calculator.R;
import com.vazk.calculator.adaptadores.CustomAdapter;
import com.vazk.calculator.adaptadores.GridView_adapter;
import com.vazk.calculator.symja.activities.ExpandAllExpressionActivity;
import com.vazk.calculator.symja.activities.FactorExpressionActivity;
import com.vazk.calculator.symja.activities.SimplifyExpressionActivity;
import com.vazk.calculator.symja.activities.SolveEquationActivity;
import com.vazk.ncalc.calculator.BasicCalculatorActivity;
import com.vazk.ncalc.calculator.LogicCalculatorActivity;
import com.vazk.ncalc.geom2d.GeometryDescartesActivity;
import com.vazk.ncalc.matrix.MatrixCalculatorActivity;
import com.vazk.ncalc.systemequations.SystemEquationActivity;
import com.vazk.ncalc.unitconverter.UnitCategoryActivity;

import java.util.ArrayList;

public class MenuAlgebra extends AppCompatActivity {
    private InterstitialAd interstitialAd;

ImageButton boton1,boton2,imagen;
    ImageView cerrar ;
    Button cerrar2;
    Dialog epicDialog;
    private CustomAdapter adaptador;
    private GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_algebra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }




        /*
        WisePlayer player = VideoKitApplication.getWisePlayerFactory().createWisePlayer();
        player.setVideoType(PlayerConstants.PlayMode.PLAY_MODE_NORMAL);
        player.setBookmark(10000);
        player.setCycleMode(PlayerConstants.CycleMode.MODE_CYCLE);
        player.setPlayUrl("https://videoplay-mos-dra.dbankcdn.com/P_VT/video_injection/92/v3/C072F990370950198572111872/MP4Mix_H.264_1920x1080_6000_HEAAC1_PVC_NoCut.mp4");

        player.ready();
*/

        interstitialAd = new InterstitialAd(this);
        // "testb4znbuh3n2" is a dedicated test ad unit ID. Before releasing your app, replace the test ad unit ID with the formal one.
        interstitialAd.setAdId("c2efjnhe28");
        loadInterstitialAd();

        interstitialAd.setAdListener(adListener);

        //PARA QUE APAREZCA LA FLECHA DE ATRAS
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                finish();
            }
        });



        grid = (GridView)  findViewById(R.id.item);

        final ArrayList<GridView_adapter> listItems = new ArrayList<>();
        listItems.add(new GridView_adapter(R.drawable.calculadora,getString(R.string.science_calculator)));
        listItems.add(new GridView_adapter((R.drawable.calculadorr), getString(R.string.logic_calculator)));
        listItems.add(new GridView_adapter((R.drawable.ecuacion), getString(R.string.solve_equation)));
        listItems.add(new GridView_adapter((R.drawable.matriz), getString(R.string.matrix)));
         listItems.add(new GridView_adapter((R.drawable.geometria), getString(R.string.nav_descartes)));
        listItems.add(new GridView_adapter((R.drawable.reducir), getString(R.string.simplify_expression)));
        listItems.add(new GridView_adapter((R.drawable.pizarron), getString(R.string.factor_polynomial)));
        listItems.add(new GridView_adapter((R.drawable.calculador), getString(R.string.all_unit_converter)));
        listItems.add(new GridView_adapter((R.drawable.pensar), getString(R.string.binomial_newton)));
        listItems.add(new GridView_adapter((R.drawable.sistem), getString(R.string.linear_system)));




        adaptador = new CustomAdapter(this, listItems);
        grid.setAdapter(adaptador);

       grid.setOnItemClickListener((adapterView, view, i, l) -> {

           if(i == 0 ){
               startActivity(new Intent(MenuAlgebra.this, BasicCalculatorActivity.class));

            }
           if(i == 1 ){
               startActivity(new Intent(MenuAlgebra.this, LogicCalculatorActivity.class));
            }
           if(i == 2 ){
              startActivity(new Intent(MenuAlgebra.this, SolveEquationActivity.class));



           }


           if(i == 3 ){
               startActivity(new Intent(MenuAlgebra.this, MatrixCalculatorActivity.class));


           }

           if(i == 4 ){
               startActivity(new Intent(MenuAlgebra.this, GeometryDescartesActivity.class));
           }

           if(i == 5){
               startActivity(new Intent(MenuAlgebra.this, SimplifyExpressionActivity.class));
           }
           if(i == 6){
               startActivity(new Intent(MenuAlgebra.this, FactorExpressionActivity.class));
               loadInterstitialAd();


           }
           if(i == 7){
               startActivity(new Intent(MenuAlgebra.this, UnitCategoryActivity.class));
               loadInterstitialAd();


           }
           if(i == 8){
               startActivity(new Intent(MenuAlgebra.this, ExpandAllExpressionActivity.class));
           }
           if(i == 9){
               startActivity(new Intent(MenuAlgebra.this, SystemEquationActivity.class));
               loadInterstitialAd();


           }
       });


    }private void loadInterstitialAd() {

        // Load an interstitial ad.
        AdParam adParam = new AdParam.Builder().build();
        interstitialAd.loadAd(adParam);

    }private void showInterstitialAd() {
        // Display the ad.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show(this);
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // Called when an ad is loaded successfully.

            showInterstitialAd();
        }
        @Override
        public void onAdFailed(int errorCode) {
            // Called when an ad fails to be loaded.

        }
        @Override
        public void onAdClosed() {
            // Called when an ad is closed.

        }
        @Override
        public void onAdClicked() {
            // Called when an ad is clicked.

        }
        @Override
        public void onAdLeave() {
            // Called when an ad leaves an app.

        }
        @Override
        public void onAdOpened() {
            // Called when an ad is opened.

        }
        @Override
        public void onAdImpression() {
            // Called when an ad impression occurs.
         }
    };

    public void mostrarInfo(){

        epicDialog = new Dialog(this);
        epicDialog.setContentView(R.layout.ayuda);
        cerrar = (ImageView) epicDialog.findViewById(R.id.cerrarVentana);
        cerrar2 = (Button) epicDialog.findViewById(R.id.botonvamo);

        cerrar.setOnClickListener(view -> epicDialog.dismiss());
        cerrar2.setOnClickListener(view -> epicDialog.dismiss());

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();



    }


}



