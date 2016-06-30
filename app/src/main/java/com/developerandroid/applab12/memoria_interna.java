package com.developerandroid.applab12;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
// Imported packages
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class memoria_interna extends AppCompatActivity {

    String EditTextString=null;
    EditText dato=null;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_interna);
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

        Button interna = (Button)findViewById(R.id.btn_interna);
        interna.setOnClickListener(OkListener);
        Button edit_data = (Button)findViewById(R.id.ver_archivo);
        edit_data.setOnClickListener(OkListenerVer);
        dato=(EditText)findViewById(R.id.data_interna);
    }

    // Adding new methods to the class
    private View.OnClickListener OkListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            onClickGuardar();
        }
    };

    private View.OnClickListener OkListenerVer = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            onClickCargar();
        }
    };

    public void onClickGuardar(){
        String str = dato.getText().toString();
        try{
            FileOutputStream fos = openFileOutput("textFile.txt",
                    MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            // Escribimos el String en el archivo
            osw.write(str);
            osw.flush();
            osw.close();
            // Mostramos que se ha guardado
            Toast.makeText(getBaseContext(), "Guardado",
                    Toast.LENGTH_SHORT).show();
            dato.setText("");
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void onClickCargar() {
        try{
            FileInputStream fis = openFileInput("textFile.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while((charRead = isr.read(inputBuffer)) > 0){
                // Convertimos los char a String
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            // Establecemos en el EditText el texto que hemos leido
            dato.setText(s);
            // Mostramos un Toast con el proceso completado
                Toast.makeText(getBaseContext(), "Cargado",
                Toast.LENGTH_SHORT).show();
            isr.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void regresar_menu(View view){
        Intent i= new Intent(this, intro.class);
        startActivity(i);
    }

}
