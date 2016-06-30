package com.developerandroid.applab12;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
// Importing packages
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import android.widget.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class memoria_externa extends AppCompatActivity {

    File file=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_externa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button externa=(Button)findViewById(R.id.externa);
        externa.setOnClickListener(OkListener);
        state_memory();
    }

    // Adding methos
    private View.OnClickListener OkListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            onClickGuardar();
        } };
    private void state_memory(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(this,"podemos escribir y leer en la memoria externa",Toast.LENGTH_LONG).show();
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(this,"solo podemos leer la memoria externa",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"algo salio mal. no puedes leer ni escribir o no esta",Toast.LENGTH_LONG).show();
        }
    }

    private void onClickGuardar(){
        EditText inputTxt=   (EditText)findViewById(R.id.data_externa);
        String datos=inputTxt.getText().toString();
        inputTxt.setText("");
        Toast.makeText(this, "Se guardo en la memoria externa",
            Toast.LENGTH_LONG).show();
            directory();
        try{
            OutputStreamWriter bf = new OutputStreamWriter(new
                    FileOutputStream(file));
            bf.write(datos);
            bf.write("\n");
            bf.write("mi segunda linea");
            Toast.makeText(this,"Estos son los datos a guardar: "+ datos + "mi segunda linea",Toast.LENGTH_LONG).show();
                    bf.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
    }

    private void directory(){
        File dir = new File(Environment.getExternalStorageDirectory() +
                "/MiDirectorio/");
        if (!dir.exists()) {
            System.out.println("creando directorio: " + dir);
            dir.mkdir();
            Toast.makeText(this,"Se creo el direcctorio",Toast.LENGTH_LONG).show();
        }
        file = new File(dir, "MiFichero.txt");
        Toast.makeText(this,"El directorio existe",Toast.LENGTH_LONG).show();
    }

    public void regresar_menu(View view){
        Intent i=new Intent(this, intro.class);
        startActivity(i);
    }

}
