package com.syt.Notepad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.syt.Notepad.dbservice.UserService;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper{

	
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "user.db", factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person (id integer primary key autoincrement, username varchar(20), paassword varchar(20))");
        db.execSQL("create table note (id integer primary key autoincrement, title varchar(20),content varchar(50),dtimes varchar(20))");
		db.execSQL("create table bill (id integer primary key autoincrement,bill varchar(20), img varchar(20), billtype varchar(20), billstate varchar(20),  logtime varchar(30))");
		db.execSQL("create table account (id integer primary key autoincrement, accountname varchar(20),money varchar(20))");
		
		List<String> accountname=new ArrayList<String>();
		accountname.add("现金");
		accountname.add("储蓄卡");
		accountname.add("信用卡");
		accountname.add("支付宝");
		List<String> money=new ArrayList<String>();
		money.add("0");
		money.add("0");
		money.add("0");
		money.add("0");
		for(int i=0;i<accountname.size();i++){
			ContentValues values=new ContentValues();
			values.put(UserService.ACCOUNT_NAME, accountname.get(i));   //ACCOUNT_NAME="accountname"
			values.put(UserService.ACCOUNT_MEMORY, money.get(i));       //ACCOUNT_MEMORY="money"
			db.insert("account", null, values);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
