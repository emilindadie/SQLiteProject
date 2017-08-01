package com.example.emili.sqliteproject.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by emili on 30/07/2017.
 */

public class ContactDbHelper  extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;

    public static final String DB_NAME = "contact.db";


    public ContactDbHelper(Context context){

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }

    public void onDownGrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }


    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = " ,";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ StoreContact.ContactEntry.DB_NAME +"("+StoreContact.ContactEntry.COLUMN_ID +" INTEGER PRIMARY KEY"
            +COMMA_SEP +StoreContact.ContactEntry.COLUMN_PRENOM + TEXT_TYPE +COMMA_SEP + StoreContact.ContactEntry.COLUMN_NOM + TEXT_TYPE +COMMA_SEP + StoreContact.ContactEntry.COLUMN_GENRE + TEXT_TYPE
            +COMMA_SEP + StoreContact.ContactEntry.COLUMN_AGE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST "+ StoreContact.ContactEntry.DB_NAME;

}


