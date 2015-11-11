package com.example.mantenimiento.intent_implicitos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String ac = intent.getAction();
        String textRecibido;

        if (ac.equals(Intent.ACTION_SEND)) {
            EditText edRecibido = (EditText) findViewById(R.id.A1EmailEditText);
            textRecibido = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (textRecibido != null) {
                edRecibido.setText(textRecibido);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void abrir(View v){
        Intent i=new Intent();
        Intent chooser=null;
        switch(v.getId()){
            case R.id.A1ButtonWeb:
                EditText edURL=(EditText)findViewById(R.id.A1UrlEditText);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(edURL.getText().toString()));
                chooser=i.createChooser(i,"Elige un navegador");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(), "Acceso a web!", Toast.LENGTH_LONG).show();
                break;

            case R.id.A1ButtonMapa:
                EditText edLongitud=(EditText)findViewById(R.id.A1LongitudEditText);
                EditText edLatitud=(EditText)findViewById(R.id.A1LatitudEditText);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:" + edLatitud.getText().toString() + "," + edLongitud.getText().toString()));
                chooser=i.createChooser(i,"Lanzar Maps");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(),"Acceso a mapas!",Toast.LENGTH_LONG).show();
                break;

            case R.id.A1ButtonEmail:
                EditText edEmail=(EditText)findViewById(R.id.A1EmailEditText);
                i.setAction(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String para[]={edEmail.getText().toString(),"kkk@lll.es","otro@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL,para);
                i.putExtra(Intent.EXTRA_SUBJECT,"Saludos desde Android");
                i.putExtra(Intent.EXTRA_TEXT,"Hola!. Este es nuestro primer email!!");
                i.setType("message/rfc822");
                chooser=i.createChooser(i,"Enviar email");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(),"EnvÃ­a el email!!",Toast.LENGTH_LONG).show();
                break;

        }

    }

}
