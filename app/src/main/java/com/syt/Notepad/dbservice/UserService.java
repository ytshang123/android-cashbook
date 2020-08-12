package com.syt.Notepad.dbservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.syt.Notepad.database.DBOpenHelper;
import com.syt.Notepad.entity.GridViewEntity;
import com.syt.Notepad.entity.ListViewEntity;
import com.syt.Notepad.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;


public class UserService {

    // person表
    public static final String PERSON_NAME = "username";
    public static final String PERSON_PASSWORD = "password";

    //note表
    public static final String TOTE_TITLE = "title";     //标题
    public static final String TOTE_CONTENT = "content";  //内容
    public static final String TOTE_DTIMES = "dtimes";    //记事时间

    //bill表
    public static final String BILL_BILL = "bill";        //消费金额
    public static final String BILL_IMG = "img";          //类型对应图片
    public static final String BILL_BILLTYPE = "billtype";   //消费类型
    public static final String BILL_SATTE = "billstate";     //支出or收入
    public static final String BILL_TIME = "logtime";        //记账时间
    public static final String BILL_ID = "id";              //每笔账对应的id

    //account表
    public static final String ACCOUNT_NAME = "accountname";     //账户类型
    public static final String ACCOUNT_MEMORY = "money";        //账户类型对应卡内金额


    private GridViewEntity gve;
    private String accountmoney;
    DBOpenHelper dbOpenHelper;

    public UserService(Context context) {
        dbOpenHelper = new DBOpenHelper(context, "", null, 1);
    }



// 增加用户信息
//	 @param pet 用户信息实例对象
    public Long insertPerson(PersonEntity pet) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();   // 对数据库进行读写的对象
        ContentValues values = new ContentValues();
        values.put(PERSON_NAME, pet.getUsername());
        values.put(PERSON_PASSWORD, pet.getPassword());
        long e = db.insert("person", null, values);
        db.close();
        return e;
    }


//	 增加note表中的数据
//	 @param ete 笔记信息实例对象
//	 @param values 笔记对象的属性值

    public Long insertNote(ListViewEntity ete) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();   // 对数据库进行读写的对象
        ContentValues values = new ContentValues();
        values.put(TOTE_TITLE, ete.getText_title_of_item());
        values.put(TOTE_CONTENT, ete.getText_content_of_item());
        values.put(TOTE_DTIMES, ete.getDtimes());
        long e = db.insert("note", null, values);
        db.close();
        return e;
    }

//  删除note表中的数据
//  @param title 表格title
    public Long deleteNote(String title) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long e = db.delete("note", "title = ?", new String[]{title});
        db.close();
        return e;
    }


//  更改note表中的数据
//  @param title 表格信息标题
//  @param values 要更改的数据
    public long updateNote(String title, ContentValues values) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long e = db.update("note", values, "title = ?", new String[]{title});
        db.close();
        return e;
    }

//  查询note表中所有的数据
    public List<ListViewEntity> findAll() {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("note", null, null, null, null, null, "id desc");
        List<ListViewEntity> list = new ArrayList<ListViewEntity>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(TOTE_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(TOTE_CONTENT));
            String dtimes = cursor.getString(cursor.getColumnIndex(TOTE_DTIMES));
            ListViewEntity entity = new ListViewEntity(title, content, dtimes);
            list.add(entity);
        }
        return list;
    }


//	 bill表中插入账单记录
//	 @param gve 账单记录对象（账户accountName，消费类型billType，收支billState，消费金额bill，消费对应的图片索引img，记账时间time）

    public Long insertBill(GridViewEntity gve) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BILL_BILL, gve.getBill());
        values.put(BILL_IMG, gve.getImg());
        values.put(BILL_BILLTYPE, gve.getBillType());
        values.put(BILL_SATTE, gve.getBillState());
        values.put(BILL_TIME, gve.getTime());
        long e = db.insert("bill", null, values);
        db.close();
        return e;
    }


//	 更新bill表中数据
//	 @param id 消费记录对应的id
//	 @param values 更新的数据

    public long updateBill(String id, ContentValues values) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long e = db.update("bill", values, " id = ? ", new String[]{id});
        db.close();
        return e;
    }

//	 删除bill表中数据
//	 @param billtype 消费记录对应的billtype
//	 @param values 更新的数据

    public long deleteBill(String billtype) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long e = db.delete("bill"," billtype = ? ", new String[]{billtype});
        db.close();
        return e;
    }


    //  更新account表中的数据  即修改对应卡内的余额
//	 @param gve 账单记录对象（账户accountName，消费类型billType，收支billState，消费金额bill，消费对应的图片索引img，记账时间time）

    public long updateAccount(GridViewEntity gve) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Double oldmoney = Double.valueOf(findAccount(gve.getAccountName()).getBill().toString().trim());
        Double newmoney = Double.valueOf(gve.getBill().toString().trim());
        int state = Integer.parseInt(gve.getBillState().toString().trim());
        // 0对应支出   1对应收入
        if (state == 0) {
            accountmoney = Double.toString(oldmoney - newmoney);
        } else if (state == 1) {
            accountmoney = Double.toString(oldmoney + newmoney);
        }
        ContentValues values = new ContentValues();
        values.put("money", accountmoney);
        long e = db.update("account", values, " accountname = ? ", new String[]{gve.getAccountName()});
        db.close();
        return e;
    }


    //  查询bill表中的所有数据
    public List<GridViewEntity> findAllBill() {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, "id desc");
        List<GridViewEntity> list = new ArrayList<GridViewEntity>();
        while (cursor.moveToNext()) {
            String dbill = cursor.getString(cursor.getColumnIndex(BILL_BILL));
            String dimg = cursor.getString(cursor.getColumnIndex(BILL_IMG));
            String dbstate = cursor.getString(cursor.getColumnIndex(BILL_SATTE));
            String dbtype = cursor.getString(cursor.getColumnIndex(BILL_BILLTYPE));
            GridViewEntity entity = new GridViewEntity();
            entity.setBillType(dbtype);
            entity.setBillState(dbstate);
            entity.setBill(dbill);
            entity.setImg(Integer.parseInt(dimg));
            list.add(entity);
        }
        return list;
    }

    //  查询account表中的所有数据
    public List<GridViewEntity> findAllAccount() {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("account", null, null, null, null, null, "id asc");
        List<GridViewEntity> list = new ArrayList<GridViewEntity>();
        while (cursor.moveToNext()) {
            String dbmoney = cursor.getString(cursor.getColumnIndex(ACCOUNT_MEMORY));
            String dbaccountname = cursor.getString(cursor.getColumnIndex(ACCOUNT_NAME));
            GridViewEntity entity = new GridViewEntity();
            entity.setAccountName(dbaccountname);
            entity.setBill(dbmoney);
            list.add(entity);
        }
        return list;
    }


    public ListViewEntity find(String id) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("note", new String[]{TOTE_TITLE, TOTE_CONTENT, TOTE_DTIMES}, "id = ? ", new String[]{id}, null, null, null);
        ListViewEntity entity = null;
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(TOTE_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(TOTE_CONTENT));
            String dtimes = cursor.getString(cursor.getColumnIndex(TOTE_DTIMES));
            entity = new ListViewEntity(title, content, dtimes);
        }
        return entity;

    }

    public GridViewEntity findAccount(String accountname) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        //查询account表对应账户名那列的数据
        Cursor cursor = db.query("account", null, "accountname = ? ", new String[]{accountname}, null, null, null);
        GridViewEntity entity = null;
        if (cursor.moveToFirst()) {
            String dbmoney = cursor.getString(cursor.getColumnIndex(ACCOUNT_MEMORY));  //查询卡内金额
            entity = new GridViewEntity();
            entity.setBill(dbmoney);
        }
        return entity;
    }


    public GridViewEntity findGve(String billtype, String time) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, "billtype=? and logtime=? ", new String[]{billtype, time}, null, null, null);
        while (cursor.moveToNext()) {
            String dbill = cursor.getString(cursor.getColumnIndex(BILL_BILL));
            String dimg = cursor.getString(cursor.getColumnIndex(BILL_IMG));
            int img = Integer.parseInt(dimg);
            String dtype = cursor.getString(cursor.getColumnIndex(BILL_BILLTYPE));
            String dstate = cursor.getString(cursor.getColumnIndex(BILL_SATTE));
            String dtime = cursor.getString(cursor.getColumnIndex(BILL_TIME));
            String did = cursor.getString(cursor.getColumnIndex(BILL_ID));
            int id = Integer.parseInt(did);
            gve = new GridViewEntity();
            gve.setBill(dbill);
            gve.setBillState(dstate);
            gve.setBillType(dtype);
            gve.setId(id);
            gve.setImg(img);
            gve.setTime(dtime);
        }
        return gve;
    }

}
