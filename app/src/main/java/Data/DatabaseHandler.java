package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACT_TABLE = "Create TABLE "+Util.TABLE_NAME+"(" +
            Util.KEY_ID+"INTEGER PRIMARY KEY,"+Util.KEY_NAME +"TEXT" +Util.KEY_PHONENUMBER +"TEXT"+")";
    db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
    onCreate(db);
    }
    public void addContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONENUMBER,contact.getPhoneNumber());

        db.insert(Util.TABLE_NAME,null,values);
        db.close();
    }
    public Contact getContact(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONENUMBER },Util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Contact contact =new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return contact;
    }
    public List<Contact> getAllContent(){
        SQLiteDatabase db=this.getWritableDatabase();
        List<Contact> contactList= new ArrayList<>();
        String selectAll="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=db.rawQuery(selectAll,null);
        if(cursor.moveToFirst()){
            do{
            Contact contact =new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setPhoneNumber(cursor.getString(2));

            contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }
}
