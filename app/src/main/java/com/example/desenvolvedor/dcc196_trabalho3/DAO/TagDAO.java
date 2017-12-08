package com.example.desenvolvedor.dcc196_trabalho3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.desenvolvedor.dcc196_trabalho3.Modelo.Tag;

import java.util.ArrayList;

public class TagDAO extends BibliotecaDbHelper {

    public TagDAO(Context context) {
        super(context);
    }

    public void inserirTag(Tag tag) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BibliotecaContract.Tag.COLUMN_TAG, tag.getTag());

        db.insert(BibliotecaContract.Tag.TABLE_NAME, null, values);
    }

    public ArrayList<Tag> getTodasTags() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(BibliotecaContract.Tag.SQL_SELECT_TAGS, null);

        ArrayList<Tag> tags = new ArrayList<>();
        while(cursor.moveToNext()) {
            Tag tag = new Tag();

            tag.setId(cursor.getInt(cursor.getColumnIndex(BibliotecaContract.Tag._ID)));
            tag.setTag(cursor.getString(cursor.getColumnIndex(BibliotecaContract.Tag.COLUMN_TAG)));

            tags.add(tag);
        }

        cursor.close();

        return tags;
    }
}