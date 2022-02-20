package com.example.pm1e14215;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Contactos.LContacto;
import conexiones.SQLiteConexion;
import conexiones.bdContactos;

public class ListaContacto extends AppCompatActivity {
    Button btnAtras;
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<LContacto> lista;
    ArrayList<String> ArregloPersonas;
    Button btnAtualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contacto);


        btnAtras = (Button) findViewById(R.id.btnRegresar);

        //Llenar lista

        conexion=new SQLiteConexion(this, bdContactos.NameDatabase, null, 1);
        listapersonas=(ListView) findViewById(R.id.listapersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);

        //doble click
        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LContacto mostrar = lista.get(i);

                Intent intent = new Intent(ListaContacto.this, ActualizarContacto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contacto", mostrar);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        // volver a pagina principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }

        private void ObtenerListaPersonas ()
        {
            SQLiteDatabase db = conexion.getReadableDatabase();
          LContacto list_personas = null;
            lista = new ArrayList<LContacto>();

            //cursor de base de dats : nos apoya a recorrer la informacion de la tabla a la cual consultamos//

            Cursor cursor = db.rawQuery("SELECT * FROM " + bdContactos.tablaContactos, null);

            //Recorremos la informacion del cursor

            while (cursor.moveToNext()) {
                list_personas = new LContacto();
                list_personas.setId(cursor.getInt(0));
               // list_personas.setId(cursor.getString(0));
                list_personas.setPais(cursor.getString(1));
                list_personas.setNombres(cursor.getString(2));
                list_personas.setTelefono(cursor.getString(3));
                list_personas.setNota(cursor.getString(4));
               // list_personas.setImagen(cursor.getBlob(5));
                lista.add(list_personas);
            }
            cursor.close();

            filllist();

        }

        private void filllist ()
        {
            ArregloPersonas = new ArrayList<String>();

            for (int i = 0; i < lista.size(); i++) {
                ArregloPersonas.add(lista.get(i).getId() + " | "
                        + lista.get(i).getPais() + " | "
                        + lista.get(i).getNombres() + " | "
                        + lista.get(i).getTelefono() + " |"
                        + lista.get(i).getNota());
            }


        }
    }