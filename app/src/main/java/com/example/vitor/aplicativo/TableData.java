package com.example.vitor.aplicativo;

/**
 * Created by Vitor on 21/04/2015.
 */
import android.provider.BaseColumns;

public class TableData {

    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {

        public static final String USER_NAME = "user_name" ;
        public static final String USER_PASS = "user_pass" ;
        public static final String DATABASE_NAME = "database_name";
        public static final String TABLE_NAME = "teste";

    }

}