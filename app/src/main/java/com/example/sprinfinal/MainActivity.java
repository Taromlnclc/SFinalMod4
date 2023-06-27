package com.example.sprinfinal;

import com.example.sprinfinal.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);  Usaremos view bidibg
        //Binding inflando
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Slide para imagenes en modo Land
        List<Integer> images = Arrays.asList(R.drawable.bsd, R.drawable.ventanas, R.drawable.linux, R.drawable.manzana, R.drawable.androide); // Agrega los recursos de imágenes que deseas mostrar
        ImageSliderAdapter adapter = new ImageSliderAdapter(this, images);
        binding.viewSlide.setAdapter(adapter);
        // Accede al Floating Action Button c lambda
        binding.fab.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "¡Vamos a contactar!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ContactActivity.class);
            startActivity(intent);
        });
    }

    //switch boton
    public void swidn(View view){
        if(binding.dianoc.isChecked()){
            guardaTema(0);
        }
        else{
            guardaTema(1);
        }
        restauraTema();
        recreate();
    }
    // Guarda el boolean del tema en SharedPreferences
    private void guardaTema(int estado) {
        SharedPreferences preferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("tema", estado);
        editor.apply();
    }
    // Restaura el boolean del tema desde SharedPreferences
    private void restauraTema() {
        SharedPreferences preferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        int tema = preferences.getInt("tema", 1);
        binding.dianoc.setChecked(tema != 1);
        if(tema==0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    //Boton de ubicacion
    public void btnUbi(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_boton);
        binding.imaUbi.startAnimation(animation);
        String location = "-41.591953, -72.688821"; // Latitud y longitud de la ubicación deseada
        Uri gmmIntentUri = Uri.parse("geo:" + location +"?q=" + location + "(casa)"); // ?q= paa que marque ubicacion xD
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
    //LOGCAT
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"On Start, sin problemas!");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"On Resume, sin problemas!");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"On Pausa, sin problemas!");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"On Stop, sin problemas!");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"On Restart, sin problemas!");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"On Destroy, sin problemas!");
    }
}
