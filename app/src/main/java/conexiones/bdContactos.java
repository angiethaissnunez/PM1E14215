package conexiones;

import java.sql.Blob;

public class bdContactos {
    public static final String NameDatabase = "PM1E14215";

    // Creacion de las tablas Contactos en la base de datos
    public static final String tablaContactos = "contactos";

    /*
      Campos especificos de la tabla contactos
    */
    public static final String id = "id";
    public static final String paises = "paises";
    public static final String nombres = "nombres";
    public static final String telefono = "telefono";
    public static final String nota = "nota";
   // public static final String imagen = "imagen";



    /* Transacciones DDL (data definition lenguage)*/

    public static final String CreateTableContactos = "CREATE TABLE " + tablaContactos +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "paises TEXT, nombres TEXT , telefono TEXT, nota TEXT )";

    public static final String DropTableContactos = "DROP TABLE IF EXISTS " + tablaContactos;

}
