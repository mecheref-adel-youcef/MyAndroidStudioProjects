package joseph.youcef.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MaBaseManager extends SQLiteOpenHelper { // create and iniciate db

    private static final String DATABASE_NAME = "GuessDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME1 = "words";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";






    public MaBaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + TABLE_NAME1 + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_WORD + " varchar(255) NOT NULL)";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql1 = "DROP TABLE IF EXISTS " + TABLE_NAME1 + ";";
        db.execSQL(sql1);
        onCreate(db);


    }

    boolean addWord(String word) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_WORD, word);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME1, null, contentValues) != -1;
    }

    Cursor getAllWords(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
    }

    Cursor getWordById(int id){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME1 +" WHERE id=?", new String[]{String.valueOf(id)});
    }


}
