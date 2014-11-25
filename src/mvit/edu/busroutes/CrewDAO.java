package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CrewDAO {
	
	public SQLiteDatabase db ;
	public DBHelper helper ;
	public String[] allColumns = {"DRIVER_PHNO" ,"BUSNO" ,"DRIVER_NAME" };
	public final static String table = "CREW";
	
	public CrewDAO(Context mycontext) throws IOException{
		helper = new DBHelper(mycontext);
		helper.createDataBase();
	}
	
	public void open(){
		db = helper.getReadableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public long addCrew(Crew newcrew){
		ContentValues values = new ContentValues();
		values.put(allColumns[0], newcrew.getDriverPhno());
		values.put(allColumns[1], newcrew.getBusNo());
		values.put(allColumns[2], newcrew.getDriverName());
		
		return db.insert(table, null, values);
	}
	
	public List<Crew> getCrewMembers(){
		List<Crew> crews = new ArrayList<Crew>();
		Cursor cur = db.rawQuery("SELECT * FROM CREW;",null);
		if(cur.moveToFirst()){
			do{
				crews.add(curToCrew(cur));
			}while(cur.moveToNext());
		}
		
		return crews ;
	}
	
	private Crew curToCrew(Cursor cur){
		Crew crew = new Crew();
		crew.setDriverPhno(cur.getString(0));
		crew.setBusNo(cur.getLong(1));
		crew.setDriverName(cur.getString(2));
		return crew ;
	}
	
	public Crew getCrewByBusNo(String busno){
		Cursor cur = db.rawQuery("SELECT * FROM CREW WHERE BUSNO = " + busno + ";", null);
		cur.moveToFirst();
		return curToCrew(cur);
	}

}
