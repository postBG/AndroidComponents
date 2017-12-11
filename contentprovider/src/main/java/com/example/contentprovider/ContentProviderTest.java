package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class ContentProviderTest extends ContentProvider {
    public static final String CP_AUTHORITY = "com.test.contentprovider";
    public static final String DB_NAME = "cptest.db";
    public static final String TABLE_NAME = "contactable";
    public static final int DB_VERSION = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://" + CP_AUTHORITY + "/" + TABLE_NAME);

    private DBHandler mDbHandler = null;
    private SQLiteDatabase mDb = null;

    @Override
    public boolean onCreate() {
        mDbHandler = new DBHandler(getContext());
        mDb = mDbHandler.getWritableDatabase();
        return mDb != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(TABLE_NAME);

        Cursor cursor = sqlBuilder.query(mDb, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = mDb.insert(TABLE_NAME, null, values);

        if (rowID > 0) {
            Uri notiuri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(notiuri, null);
            return notiuri;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
