package com.map.HaNhatHuy.bt1.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.map.HaNhatHuy.bt1.dao.DBHelper;
import com.map.HaNhatHuy.bt1.dao.UserDAO;
import com.map.HaNhatHuy.bt1.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public UserDAOImpl(Context context){
        dbHelper = new DBHelper(context);
        try {
            database = dbHelper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately in your app
        }
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    @Override
    public boolean add(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getUserContentValues(user);
        long result = db.insert("users", null, values);
        db.close();
        return result != -1;
    }

    @Override
    public boolean edit(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getUserContentValues(user);
        int result = db.update("users", values, "id = ?", new String[]{String.valueOf(user.getId())});
        db.close();
        return result > 0;
    }

    @Override
    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("users", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    @Override
    public ArrayList<User> search(String keyword) {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM users WHERE username LIKE ? OR name LIKE ? OR email LIKE ?";
        String[] selectionArgs = new String[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                User user = getUserFromCursor(cursor);
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    @Override
    public User getUserById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM users WHERE id = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        User user = null;
        if (cursor.moveToFirst()) {
            user = getUserFromCursor(cursor);
        }
        cursor.close();
        db.close();
        return user;
    }

    private ContentValues getUserContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("telephoneNum", user.getTelephoneNum());
        values.put("gender", user.getGender());
        values.put("dob", dateFormat.format(user.getDob()));
        return values;
    }

    private User getUserFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String username = cursor.getString(cursor.getColumnIndex("username"));
        String password = cursor.getString(cursor.getColumnIndex("password"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String telephoneNum = cursor.getString(cursor.getColumnIndex("telephoneNum"));
        String gender = cursor.getString(cursor.getColumnIndex("gender"));
        String dobString = cursor.getString(cursor.getColumnIndex("dob"));

        Date dob = null;
        try {
            dob = dateFormat.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new User(id, username, password, name, email, telephoneNum, gender, dob);
    }
}
