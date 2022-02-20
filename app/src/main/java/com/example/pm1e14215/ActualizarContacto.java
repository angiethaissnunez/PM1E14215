package com.example.pm1e14215;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Contactos.LContacto;
import conexiones.SQLiteConexion;
import conexiones.bdContactos;

public class ActualizarContacto extends AppCompatActivity {
    Button btnAtras, btnllamar;
    SQLiteConexion conexion;
    EditText paises;
    EditText id, nombres, telefono, nota, nume, nomb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contacto);

        conexion=new SQLiteConexion(this, bdContactos.NameDatabase, null, 1);

        //Botones
        Button consulta = (Button) findViewById(R.id.btnLlamar);
        Button eliminar = (Button) findViewById(R.id.btnEliminar);
        Button actualizar = (Button) findViewById(R.id.btnActualizae2);


        id = (EditText) findViewById(R.id.txtID);
        paises = (EditText) findViewById(R.id.txtpais);
        nombres = (EditText) findViewById(R.id.txtNombre2);
        telefono = (EditText) findViewById(R.id.txtCell2);
        nota = (EditText) findViewById(R.id.txtNota2);
        btnAtras = (Button) findViewById(R.id.btnVolver);


        Bundle obj = getIntent().getExtras();

        LContacto mostra = null;

        if (obj != null) {
            mostra = (LContacto) obj.getSerializable("contacto");

            id.setText(mostra.getId().toString());
            paises.setText(mostra.getPais().toString());
            nombres.setText(mostra.getNombres().toString());
            telefono.setText(mostra.getTelefono().toString());
            nota.setText(mostra.getNota().toString());
        }

        // volver a pagina principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombres.getText().toString().isEmpty() && !telefono.getText().toString().isEmpty() && !nota.getText().toString().isEmpty() ){
                    Actualizar();
                }
                else{
                    Toast.makeText(getApplicationContext()," Llenar los espacios vacios" ,Toast.LENGTH_LONG).show();

                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

        //FUNCION LLAMAR
        btnllamar = findViewById(R.id.btnLlamar);
        nume = findViewById(R.id.txtCell2);
        nomb = findViewById(R.id.txtNombre2);

        btnllamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarContacto.this);
                builder.setMessage("¿Desea llamar a  " + nomb.getText().toString() + "?")
                        .setTitle("Acción");

                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + nume.getText().toString()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        //Parametros de configuracion de la sentencia SELECT
        String[] params = {id.getText().toString()}; //parametro de la busqueda
        String[] fields = {bdContactos.paises,
                bdContactos.nombres,
                bdContactos.telefono,
                bdContactos.nota};
        String wherecond = bdContactos.id + "=?";

        try {
            Cursor cdata = db.query(bdContactos.tablaContactos, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();
            paises.setText(cdata.getString(0));
            nombres.setText(cdata.getString(1));
            nota.setText(cdata.getString(2));
            telefono.setText(cdata.getString(3));
           // direccion.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(), "Consultado con exito", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            limpiar();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String[] params = {id.getText().toString()};
        String wherecond = bdContactos.id + "=?";
        db.delete(bdContactos.tablaContactos, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        limpiar();
    }

    private void Actualizar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(bdContactos.paises, paises.getText().toString());
        valores.put(bdContactos.nombres, nombres.getText().toString());
        valores.put(bdContactos.telefono, telefono.getText().toString());
        valores.put(bdContactos.nota, nota.getText().toString());
        db.update(bdContactos.tablaContactos, valores, bdContactos.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
        limpiar();
    }

    void limpiar(){
        id.setText("");
        paises.setText("");
        nombres.setText("");
        telefono.setText("");
        nota.setText("");


    }
}