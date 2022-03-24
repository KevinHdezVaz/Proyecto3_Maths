package com.vazk.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.analytics.HiAnalytics;
import com.huawei.hms.analytics.HiAnalyticsInstance;
import com.huawei.hms.analytics.HiAnalyticsTools;
import com.huawei.hms.analytics.type.ReportPolicy;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.kit.awareness.Awareness;
import com.huawei.hms.kit.awareness.barrier.AwarenessBarrier;
import com.huawei.hms.kit.awareness.barrier.BarrierStatus;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;
import com.huawei.hms.kit.awareness.barrier.WifiBarrier;
import com.huawei.hms.kit.awareness.capture.WifiStatusResponse;
import com.huawei.hms.kit.awareness.status.WifiStatus;
 import com.vazk.calculator.menus.MenuAlgebra;
import com.vazk.calculator.menus.MenuEstadistica;
import com.vazk.calculator.menus.MenuNumeros;
import com.vazk.calculator.menus.menuCalculo;

import java.util.HashSet;
import java.util.Set;

public class PrincipalMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView nav;
    MenuItem tools, home,home2 ,home3,home4,home5;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView imagen1,imagen2,imagen3,imagen4,imagen5;
    ImageButton btn1, btn2, btn3, btn4;
    LottieAnimationView animationView,animationView2;
     ImageButton boton1,boton2,imagen,imaggen2;
    ImageView cerrar ;
    Button cerrar2,seguir;
    Dialog epicDialog,epicDialog2;
    TextView estatus;
    Boolean activado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_menu);


        //analytics
        HiAnalyticsInstance instance = HiAnalytics.getInstance(this);
        // Enable SDK log recording.
        HiAnalyticsTools.enableLog();

        // Or initialize Analytics Kit with the given context.

        instance.setUserProfile("userKey","value");

// Used to report an event upon app switching to the background.
        ReportPolicy moveBackgroundPolicy = ReportPolicy.ON_MOVE_BACKGROUND_POLICY;
// Used to report an event at the specified interval.
        ReportPolicy scheduledTimePolicy = ReportPolicy.ON_SCHEDULED_TIME_POLICY;
// Set the event reporting interval to 600 seconds.
        scheduledTimePolicy.setThreshold(600);
        Set<ReportPolicy> reportPolicies = new HashSet<>();
// Add the ON_SCHEDULED_TIME_POLICY and ON_MOVE_BACKGROUND_POLICY policies.
        reportPolicies.add(scheduledTimePolicy);
        reportPolicies.add(moveBackgroundPolicy);
// Set the ON_MOVE_BACKGROUND_POLICY and ON_SCHEDULED_TIME_POLICY policies.
        instance.setReportPolicies(reportPolicies);


        imagen = findViewById(R.id.informacion);

        imaggen2 = findViewById(R.id.encuesta);
        imaggen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalMenu.this,Encuesta.class));
            }
        });
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarInfo();
            }
        });
        nav=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        HwAds.init(this);
        tools =  nav.getMenu().findItem(R.id.home);
        home2 =  nav.getMenu().findItem(R.id.nav_acerca);
        btn1 = (ImageButton) findViewById(R.id.hola);
        btn2 = (ImageButton) findViewById(R.id.hola2);
        btn3 = (ImageButton) findViewById(R.id.hola3);
        btn4= (ImageButton) findViewById(R.id.hola4);

        imagen1 = findViewById(R.id.animacion_1);
        imagen2 = findViewById(R.id.animacion_2);
        imagen3 = findViewById(R.id.animacion_3);
        imagen4 = findViewById(R.id.animacion_4);
        animationView = findViewById(R.id.animationView);
        animationView2 = findViewById(R.id.animationView2);

        animationView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_arriba));
        animationView2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_abajo));



        AwarenessBarrier connectingBarrier = WifiBarrier.connecting();
        final String BARRIER_RECEIVER_ACTION = getApplication().getPackageName() + "WIFI_BARRIER_RECEIVER_ACTION";
        Intent intent2 = new Intent(BARRIER_RECEIVER_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        WifiBarrierReceiver barrierReceiver = new WifiBarrierReceiver();
        registerReceiver(barrierReceiver, new IntentFilter(BARRIER_RECEIVER_ACTION));

        String wifiBarrierLabel = "wifi connecting barrier";
        BarrierUpdateRequest.Builder builder = new BarrierUpdateRequest.Builder();
        BarrierUpdateRequest request = builder.addBarrier(wifiBarrierLabel, connectingBarrier,pendingIntent).build();
        Awareness.getBarrierClient(this).updateBarriers(request)
                // Callback listener for execution success.
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                     }
                })
                // Callback listener for execution failure.
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                         Log.e("GS", "add barrier failed", e);
                    }
                });


        imagen1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_abajo));
        imagen2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_left_quick_math));
        imagen3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_right_0));
        imagen4.startAnimation(AnimationUtils.loadAnimation(this,R.anim.from_arriba));




        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

         home3 =  nav.getMenu().findItem(R.id.nav_share);
        home4 =  nav.getMenu().findItem(R.id.nav_profile);



        drawerLayout=findViewById(R.id.drawer_layout);

        btn1.setOnClickListener(view -> {
             Intent intent = new Intent(PrincipalMenu.this, MenuAlgebra.class);
            startActivity(intent);
        });

        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalMenu.this, menuCalculo.class);
            startActivity(intent);
        });

        btn3.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalMenu.this, MenuEstadistica.class);
            startActivity(intent);
        });


        btn4.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalMenu.this, MenuNumeros.class);
            startActivity(intent);
        });



    }


    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    public void onReceive(Context context, Intent intent) {
        BarrierStatus barrierStatus = BarrierStatus.extract(intent);
        String label = barrierStatus.getBarrierLabel();
        switch(barrierStatus.getPresentStatus()) {
            case BarrierStatus.TRUE:
                activado = true;
                Log.i("HLA", label + " status:true");
                Toast.makeText(context, "actviado", Toast.LENGTH_SHORT).show();
                break;
            case BarrierStatus.FALSE:
                activado = false;
                Toast.makeText(context, "desactivado", Toast.LENGTH_SHORT).show();

                Log.i("HOLA", label + " status:false");
                break;
            case BarrierStatus.UNKNOWN:
                Log.i("HOLA", label + " status:unknown");
                break;
        }
    }

    public void mostrarInfo(){

        epicDialog = new Dialog(this);
        epicDialog.setContentView(R.layout.ayuda);
        cerrar = (ImageView) epicDialog.findViewById(R.id.cerrarVentana);
        cerrar2 = (Button) epicDialog.findViewById(R.id.botonvamo);
        estatus = (TextView)epicDialog.findViewById(R.id.estadoVar);
        cerrar.setOnClickListener(view -> epicDialog.dismiss());
        cerrar2.setOnClickListener(view -> epicDialog.dismiss());

         epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


        Awareness.getCaptureClient(this).getWifiStatus()
                // Callback listener for execution success.
                .addOnSuccessListener(wifiStatusResponse -> {
                    WifiStatus status = wifiStatusResponse.getWifiStatus();

                    switch (status.getStatus()) {
                        case WifiStatus.CONNECTED:
                            estatus.setText("Estado del Wi-fi: ACTIVADO");

                            break;
                        case WifiStatus.ENABLED:

                            estatus.setText("Estado del Wi-fi: ACTIVADO SIN CONEXIÓN");

                            break;

                        case WifiStatus.DISABLED:

                            estatus.setText("Estado del Wi-fi: DESACTIVADO");

                            break;
                        default:
                            break;
                    }
                 })
                // Callback listener for execution failure.
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                     }
                });



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_acerca:
                mostrarSalir();
                break;

            case R.id.nav_share:


                Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
                compartir.setType("text/plain");
                String mensaje = "SoluMath - Aprende todo en uno. \nApp completa con todo lo que un estudiante necesita, un pack completo!";
                compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "Aplicación Todo en uno");
                compartir.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
                startActivity(Intent.createChooser(compartir, "Compartir Via"));
                break;



            case R.id.nav_profile:

                String url = "https://kevinhernandez.netlify.app/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }

    public void mostrarSalir() {
        epicDialog2 = new Dialog(this);
        epicDialog2.setContentView(R.layout.about2);

        seguir = (Button) epicDialog2.findViewById(R.id.botonvamo);



        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              epicDialog2.dismiss();



            }
        });

        epicDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        epicDialog2.show();


    }

}

class WifiBarrierReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        BarrierStatus barrierStatus = BarrierStatus.extract(intent);
        String label = barrierStatus.getBarrierLabel();
        switch(barrierStatus.getPresentStatus()) {
            case BarrierStatus.TRUE:
                Log.i("A", label + " status:true");
                break;
            case BarrierStatus.FALSE:
                Log.i("SD", label + " status:false");
                break;
            case BarrierStatus.UNKNOWN:
                Log.i("DS", label + " status:unknown");
                break;
        }
    }
}