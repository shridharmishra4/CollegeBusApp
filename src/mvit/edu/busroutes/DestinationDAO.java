package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DestinationDAO {
	
	public SQLiteDatabase db ;
	public DBHelper helper ;
	public String[] allColumns = {"BUSNO" , "DESTINATION" , "MORNING_DEPARTURE"};
	public final static String table = "DESTINATION";
	
	public DestinationDAO(Context mycontext) throws IOException{
		helper = new DBHelper(mycontext);
		helper.createDataBase();
	}
	
	public void open(){
		db = helper.getReadableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public long addDestination(Destination newdestination){
		ContentValues values = new ContentValues();
		values.put(allColumns[0], newdestination.getBusno());
		values.put(allColumns[1], newdestination.getDestination());
		values.put(allColumns[2], newdestination.getMorning_dept());
		
		return db.insert(table, null, values);
	}
	
	public List<Destination> getDestinationMembers(){
		List<Destination> destinations = new ArrayList<Destination>();
		Cursor cur = db.rawQuery("SELECT * FROM DESTINATION ORDER BY DESTINATION;",null);
		if(cur.moveToFirst()){
			do{
				destinations.add(curToDestination(cur));
			}while(cur.moveToNext());
		}
		
		return destinations ;
	}
	
	private Destination curToDestination(Cursor cur){
		Destination destination = new Destination();
		destination.setBusno(cur.getLong(0));
		destination.setDestination(cur.getString(1));
		destination.setMorning_dept(cur.getString(2));
		return destination ;

	}
	
	public List<String> getdestinations(){
		List<String> destinations = new ArrayList<String>();
		Cursor cur = db.rawQuery("SELECT DISTINCT DESTINATION FROM DESTINATION ORDER BY DESTINATION;", null);
		cur.moveToFirst();
		do{
			destinations.add(cur.getString(0));
		}while(cur.moveToNext());
		
		return destinations;
	}
	
	public List<String> getdestinations(String busno){
		List<String> destinations = new ArrayList<String>();
		Cursor cur = db.rawQuery("SELECT DISTINCT DESTINATION FROM DESTINATION WHERE BUSNO=" + busno + " ORDER BY MORNING_DEPARTURE;", null);
		cur.moveToFirst();
		do{
			destinations.add(cur.getString(0));
		}while(cur.moveToNext());
		
		return destinations;
	}
	
	public List<String> getTime(String busno){
		List<String> time = new ArrayList<String>();
		Cursor cur = db.rawQuery("SELECT DISTINCT MORNING_DEPARTURE FROM DESTINATION WHERE BUSNO=" + busno + " ORDER BY MORNING_DEPARTURE;", null);
		cur.moveToFirst();
		do{
			time.add(cur.getString(0));
		}while(cur.moveToNext());
		
		return time;
	}

}
