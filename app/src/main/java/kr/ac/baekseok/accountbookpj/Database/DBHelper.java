package kr.ac.baekseok.accountbookpj.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "accountBook.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_HISTORY = "history";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TOTAL_AMOUNT = "total_amount";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_HISTORY + " TEXT, " +
                COLUMN_CATEGORY + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getDataByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_DATE, COLUMN_AMOUNT, COLUMN_HISTORY, COLUMN_CATEGORY};
        String selection = COLUMN_DATE + " = ?";
        String[] selectionArgs = {date};
        return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
    }

    public double getTotalAmountByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_AMOUNT};
        String selection = COLUMN_DATE + " = ?";
        String[] selectionArgs = {date};
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        double totalAmount = 0;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT));
                totalAmount += amount;
            } while (cursor.moveToNext());
            cursor.close();
        }

        return totalAmount;
    }

    public void insertRecord(String date, String amount, String history, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_AMOUNT, amount);
        values.put(COLUMN_HISTORY, history);
        values.put(COLUMN_CATEGORY, category);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Cursor getDataByDatePattern(String datePattern) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_DATE, COLUMN_AMOUNT, COLUMN_HISTORY, COLUMN_CATEGORY};
        String selection = COLUMN_DATE + " LIKE ?";
        String[] selectionArgs = {datePattern};
        return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
    }
    public Cursor getCategoryTotalAmount(String datePattern) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_CATEGORY, "SUM(" + COLUMN_AMOUNT + ") AS " + COLUMN_TOTAL_AMOUNT};
        String selection = COLUMN_DATE + " LIKE ?";
        String[] selectionArgs = {datePattern};
        String groupBy = COLUMN_CATEGORY;
        return db.query(TABLE_NAME, projection, selection, selectionArgs, groupBy, null, null);
    }
    public void deleteData(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_CATEGORY + " = ?", new String[]{category});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public double getTotalAmountByMonth(String datePattern) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_DATE, COLUMN_AMOUNT, COLUMN_HISTORY, COLUMN_CATEGORY};
        String selection = COLUMN_DATE + " LIKE ?";
        String[] selectionArgs = {datePattern};
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        int totalMonthAmount = 0;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                double amount2 = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT));
                totalMonthAmount += amount2;
            } while (cursor.moveToNext());
            cursor.close();
        }

        return totalMonthAmount;
    }
}
