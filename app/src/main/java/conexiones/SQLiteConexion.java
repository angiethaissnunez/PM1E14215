package conexiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConexion extends SQLiteOpenHelper {

    public SQLiteConexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(bdContactos.CreateTableContactos);
        sqLiteDatabase.execSQL(bdPaises.CreateTableAggPaises);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(bdContactos.DropTableContactos);
        sqLiteDatabase.execSQL(bdPaises.DropTableAggPaises);
        onCreate(sqLiteDatabase);
    }
}
