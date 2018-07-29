package com.mobileapps.training.daily4;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mobileapps.training.daily4.data.LocalDataSource;


public class AnimalsProvider extends ContentProvider {

    private static final String TAG = "AnimalsProvider";

    public static final String CONTENT_AUTHORITY = "com.mobileapps.training";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String TABLE_NAME = "animal";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(TABLE_NAME)
            .build();

    public static final int GET_ALL = 1;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private LocalDataSource localDataSource;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        /*
         * For each type of URI you want to add, create a corresponding code. Preferably, these are
         * constant fields in your class so that you can use them throughout the class and you no
         * they aren't going to change. In Todo, we use CODE_TODO or CODE_TODO_WITH_ID.
         */

        /* This URI is content://com.example.todo/todo/ */
        matcher.addURI(authority, TABLE_NAME, GET_ALL);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: Creatin database");
        localDataSource = new LocalDataSource(getContext());
        return localDataSource != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = localDataSource.getReadableDatabase();
        int matcher = sUriMatcher.match(uri);
        switch (matcher) {
            case GET_ALL:
                cursor = sqLiteDatabase.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case GET_ALL:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + "vnd." + CONTENT_AUTHORITY + "/" + TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + sUriMatcher.match(uri));
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
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
