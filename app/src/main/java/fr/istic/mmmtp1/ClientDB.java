package fr.istic.mmmtp1;

import android.database.sqlite.SQLiteDatabase;

/**
 *
 */
public class ClientDB {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_CITY = "city";

    private static final String LOG_TAG = "ClientDB";
    public static final String SQLITE_TABLE = "CLIENTS";

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NAME + "," +
                    KEY_DATE + "," +
                    KEY_CITY + "," +
                    " UNIQUE (" + KEY_NAME +"," + KEY_DATE + "));";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
        onCreate(db);
    }
}
