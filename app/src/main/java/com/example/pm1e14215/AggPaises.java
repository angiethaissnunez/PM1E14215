package com.example.pm1e14215;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import conexiones.SQLiteConexion;
import conexiones.bdPaises;

public class AggPaises extends AppCompatActivity {
    Button btnvolver2, btnSalvarPai;
    EditText PaisId, nombrePais, codigoPais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agg_paises);


        btnvolver2 = (Button) findViewById(R.id.btnVolver2);
        PaisId = (EditText) findViewById(R.id.txtIDPais);
        nombrePais = (EditText) findViewById(R.id.txtNomPais);
        codigoPais = (EditText) findViewById(R.id.txtNumCodigo);

        btnSalvarPai = (Button) findViewById(R.id.btnIngPais);

        // volver a pagina principal
        btnvolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                            }
        });

        btnSalvarPai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nombrePais.getText().toString().isEmpty() && !codigoPais.getText().toString().isEmpty())
                {
                    AgregarPaises();
                }
                else{
                    Mensaje();
                }
            }
        });

    }
    private void Mensaje() {
        mensajeee alerta = new mensajeee();
        alerta.show(getSupportFragmentManager(),"Mensaje");
    }

    private void AgregarPaises() {
        SQLiteConexion conexion = new SQLiteConexion(this, bdPaises.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(bdPaises.nombrepais, nombrePais.getText().toString());
        valores.put(bdPaises.codigopais, codigoPais.getText().toString());

        Long resultado = db.insert(bdPaises.tablaAggPaises, bdPaises.idpais, valores);

        Toast.makeText(getApplicationContext(),
                "Registro ingresado con exito!! Codigo " + resultado.toString(),
                Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
    }

    private void LimpiarPantalla() {
        PaisId.setText("");
        nombrePais.setText("");
        codigoPais.setText("");
    }

}