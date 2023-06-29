package com.example.sprinfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.example.sprinfinal.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    ActivityContactBinding binContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_contact);
        //Binding inflando
        binContacto = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binContacto.getRoot());

    }
    // Abre la web de linkedin
    public void botonLinkedin(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_boton);
        binContacto.miLinkedin.startAnimation(animation);
        String url = "https://www.linkedin.com/in/lcornejo85";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Abro Whatsapp y mensaje predeterminado
    public void botonWhatsapp(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_boton);
        binContacto.miWhatsapp.startAnimation(animation);
        String url = "https://api.whatsapp.com/send/?phone=56968952068&text=Hola%20deseo%20contactar%20contigo...&type=phone_number&app_absent=0";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        //enviarMensajeWhatsApp();
    }
    //Este es otro metodo pero abre solo whatsapp y si no esta el contacto xD
    private void enviarMensajeWhatsApp() {
        try {
            Intent sendIntent = new Intent(); sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hola, deseo contactar con usted.");
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp no está..", Toast.LENGTH_LONG).show();
        }
    }

    // Envia SMS debe asignar permiso de SMS
    public void botonSms(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_boton);
        binContacto.miSms.startAnimation(animation);
        enviarMensaje();
    }
    private void enviarMensaje() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+56 9 6895 2068", null, "Hola, deseo contactar con usted.", null, null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado correctamente.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error al enviar el mensaje.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Da a elegir el cliente de correo, con un predeterminado mensaje y correo a enviar
    public void botonGmail(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_boton);
        binContacto.miGmail.startAnimation(animation);
        enviarCorreoElectronico();
    }
    private void enviarCorreoElectronico() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "ltrcornejo@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contactar");
        intent.putExtra(Intent.EXTRA_TEXT, "Hola, deseo contactar con usted.");
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Elije un cliente de Correo:"));
    }

}