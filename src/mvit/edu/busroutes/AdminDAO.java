package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AdminDAO {
	
	public SQLiteDatabase db ;
	public DBHelper helper ;
	public String[] allColumns = {"ADMIN_NAME","ADMIN_PASS"};
	public final static String table = "ADMIN";

	public AdminDAO(Context mycontext) throws IOException{
		helper = new DBHelper(mycontext);
		helper.createDataBase();
	}
	
	public void open(){
		db = helper.getReadableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public long addAdmin(Admin newadmin){
		ContentValues values = new ContentValues();
		values.put(allColumns[0], newadmin.getAdmin_name());
		values.put(allColumns[1], newadmin.getAdmin_pass());
		return db.insert(table, null, values);
	}
	
	public List<Admin> getAdmins(){
		List<Admin> admins = new ArrayList<Admin>();
		Cursor cur = db.rawQuery("SELECT * FROM ADMIN;",null);
		if(cur.moveToFirst()){
			do{
				admins.add(curToAdmin(cur));
			}while(cur.moveToNext());
		}
		
		return admins ;
	}
	
	private Admin curToAdmin(Cursor cur){
		Admin admin = new Admin();
		admin.setAdmin_name(cur.getString(0));
		admin.setAdmin_pass(cur.getString(1));
		
		return admin ;
	}
	
	private AdminDetail curToAdminDetail(Cursor cur){
		AdminDetail admin = new AdminDetail();
		admin.setAdmin_name(cur.getString(0));
		admin.setDesignation(cur.getString(1));
		admin.setGender(cur.getString(2));	
		admin.setPhone(Long.toString(cur.getLong(3)));
		admin.setEmail(cur.getString(4));
		
		return admin ;
	}
	
	public String getPassByUser(String user) throws SQLException , CursorIndexOutOfBoundsException{
			Cursor cur = db.rawQuery("SELECT ADMIN_PASS FROM ADMIN WHERE ADMIN_NAME = '" + user + "';" , null);
			cur.moveToFirst();
			return cur.getString(0);
	}
	
	public Admin getAdmin(String admin){
		Cursor cur = db.rawQuery("SELECT * FROM ADMIN WHERE ADMIN_NAME = '" + admin + "';", null);
		cur.moveToFirst();
		return curToAdmin(cur);
	}
	
	public AdminDetail getAdminDetail(String name){
		Cursor cur = db.rawQuery("SELECT * FROM ADMINDETAIL WHERE ADMIN_NAME = '" + name + "';", null);
		cur.moveToFirst();
		return curToAdminDetail(cur);
	}

}
