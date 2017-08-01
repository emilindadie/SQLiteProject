package com.example.emili.sqliteproject.donnee;

import android.provider.BaseColumns;

/**
 * Created by emili on 30/07/2017.
 */

public final class StoreContact {


    public  StoreContact(){

    }

    public static abstract class ContactEntry implements BaseColumns{

        public static final String DB_NAME = "contact";

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_PRENOM = "prenom";

        public static final String COLUMN_NOM = "nom";

        public static final String COLUMN_GENRE = "genre";

        public static final String COLUMN_AGE = "age";

    }
}
