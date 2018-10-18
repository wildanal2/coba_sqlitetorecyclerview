package com.sqliterecycler.com.sqlite_recycler;

import android.provider.BaseColumns;

public class ItemContract {

    public ItemContract() {
    }

    public static final class ItemEntry implements BaseColumns{
        public static final String TABLE_NAME = "BelanjaanList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_JUMLAH = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}
