package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joey on 7/13/16.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG =MySQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String ICON_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String[] ICON_COLUMNS = {COL_ID, COL_ITEM_NAME, COL_DESCRIPTION, COL_PRICE, COL_TYPE};

    private static final String CREATE_ICON_LIST_TABLE =
            "CREATE TABLE " + ICON_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT )";


    private static MySQLiteOpenHelper instance;

    public static MySQLiteOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new MySQLiteOpenHelper(context);
        }
        return instance;
    }

    private MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ICON_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ICON_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public Cursor getIconList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ICON_LIST_TABLE_NAME, // a. table
                ICON_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }
}
