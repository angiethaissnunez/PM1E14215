package com.example.pm1e14215;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Contactos.LPaises;
import conexiones.SQLiteConexion;
import conexiones.bdContactos;
import conexiones.bdPaises;

public class MainActivity extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText nombres, telefono, nota;
    //String pais;
    Spinner paises;
    Button btnGuardar,btnFoto, btnLista;

    String spinPais; //Contenido del pais
    FloatingActionButton agregarpa;
   // ArrayList<String> lista_paises;
   // ArrayList<LPaises> listaPais;

    ImageView ObjImagen;
    String CurrentPhotoPath;
    static final int PETICION_ACCESO_CAM = 100;
    static final int TAKE_PIC_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /* conexion = new SQLiteConexion(this, bdPaises.NameDatabase, null, 1);
        paises = (Spinner) findViewById(R.id.spnPaises);*/

       paises = (Spinner) findViewById(R.id.spnPaises);
        nombres = (EditText) findViewById(R.id.txtNombre);
        telefono = (EditText) findViewById(R.id.txtCell);
        nota = (EditText) findViewById(R.id.txtNota);
        ObjImagen = (ImageView) findViewById(R.id.objImagen);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnFoto = (Button) findViewById(R.id.btnTomarFoto);
        btnLista = (Button) findViewById(R.id.btnLista);
        agregarpa = (FloatingActionButton) findViewById(R.id.btnAgregarPaises);


       /* ObtenerListaPaises();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista_paises);
        paises.setAdapter(adp);*/

        //ir activity lista
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaContacto.class);
                startActivity(intent);
            }
        });

       /* //ir activity agregar paises
        agregarpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AggPaises.class);
                startActivity(intent);
            }
        });*/

        agregarpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AggPaises.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        //Combobox paises
       /* paises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    pais=paises.getSelectedItem().toString();

                } catch (Exception ex)
                {
                    ex.toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


        btnGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nombres=(EditText) findViewById(R.id.txtNombre);
                nota=(EditText) findViewById((R.id.txtNota));
                telefono=(EditText) findViewById((R.id.txtCell));

                if(!nombres.getText().toString().isEmpty() && !nota.getText().toString().isEmpty() && !telefono.getText().toString().isEmpty()){
                    if(telefono.getText().length()<8)
                    {
                        Toast.makeText(getApplicationContext(),"El numero debe ser mayor a 8 digitos ",Toast.LENGTH_LONG).show();
                    }

                    else{
                        AgregarPersona();
                    }
                }
                else{
                    Mensaje();
                }
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });





    }

    public void Mensaje(){
        mensajeee mensaje=new mensajeee();
        mensaje.show(getSupportFragmentManager(),"Mensaje");
    }

   /* private void ObtenerListaPaises() {
        LPaises paiss = null;
        listaPais = new ArrayList<LPaises>();

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + bdPaises.tablaAggPaises, null);

        while (cursor.moveToNext()){
            paiss = new LPaises();
            paiss.setIdpais(cursor.getString(0));
            paiss.setNombrepais(cursor.getString(1));
            paiss.setCodigopais(cursor.getString(2));

            listaPais.add(paiss);
        }
        cursor.close();
        fillcombobox();
    }*/

    //Rellenando el Spinner
    /*private void fillcombobox() {
        lista_paises = new ArrayList<String>();
        for(int i=0; i < listaPais.size(); i++){
            lista_paises.add(listaPais.get(i).getNombrePais()+ " (" +
                    listaPais.get(i).getCodigopais()+ ")");
        }
    }*/

    private void AgregarPersona()
    {

        SQLiteConexion conexion = new SQLiteConexion(this, bdContactos.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(bdContactos.paises, paises.getSelectedItem().toString());
        valores.put(bdContactos.nombres, nombres.getText().toString());
        valores.put(bdContactos.telefono, telefono.getText().toString());
        valores.put(bdContactos.nota, nota.getText().toString());


        Long resultado = db.insert(bdContactos.tablaContactos,bdContactos.id, valores);

        Toast.makeText(getApplicationContext(),"Guardado Correctamente!! Codigo "+ resultado.toString(),
                Toast.LENGTH_LONG).show();

        db.close();


        LimpiarPatalla();

    }
    //Limpiar Pantalla
    private void LimpiarPatalla()
    {
        nombres.setText("");
        telefono.setText("");
        nota.setText("");

    }

    //Permisos
    private void permisos()
    {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PETICION_ACCESO_CAM);
        }
        else
        {
            capturarfoto();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PETICION_ACCESO_CAM)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                capturarfoto();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Se requiere permisos de acceso a la camara", Toast.LENGTH_LONG).show();
        }
    }

    private void capturarfoto()
    {
        Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takepic.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takepic, TAKE_PIC_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Byte[] arreglo;

        if(requestCode == TAKE_PIC_REQUEST && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            ObjImagen.setImageBitmap(imagen);
        }

    }

    }
