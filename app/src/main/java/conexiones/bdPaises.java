package conexiones;

public class bdPaises {


    public static final String NameDatabase = "PM1E14215";

    public static final String tablaAggPaises = "paises";
    public static final String idpais = "idpais";
    public static final String nombrepais = "nombrepais";
    public static final String codigopais = "codigopais";

    public static final String CreateTableAggPaises = "CREATE TABLE " + tablaAggPaises +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombrepais TEXT, codigopais TEXT)";

    public static final String DropTableAggPaises = "DROP TABLE IF EXISTS " + tablaAggPaises;

}
