package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DistanceDAO {
	
	public SQLiteDatabase db ;
	public DBHelper helper ;
	public String[] allColumns = {"DESTINATION" , "DISTANCE"};
	public final static String table = "DISTANCE";
	
	public DistanceDAO(Context mycontext) throws IOException{
		helper = new DBHelper(mycontext);
		helper.createDataBase();
	}
	
	public void open(){
		db = helper.getReadableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public long addDistance(Distance newdistance){
		ContentValues values = new ContentValues();
		values.put(allColumns[0], newdistance.getDestination());
		values.put(allColumns[1], newdistance.getDistance());
		return db.insert(table, null, values);
	}
	
	public List<Distance> getDistances(){
		List<Distance> distances = new ArrayList<Distance>();
		Cursor cur = db.rawQuery("SELECT * FROM DISTANCE ;",null);
		if(cur.moveToFirst()){
			do{
				distances.add(curToDistance(cur));
			}while(cur.moveToNext());
		}
		
		return distances ;
	}
	
	private Distance curToDistance(Cursor cur){
		Distance distance = new Distance();
		
		distance.setDestination(cur.getString(0));
		distance.setDistance(cur.getLong(1));
		return distance ;
	}
	
	public String getDistanceOfStop(String busstop){
		Cursor cur = db.rawQuery("SELECT DISTANCE FROM DISTANCE WHERE DESTINATION = '" + busstop + "';", null);
		cur.moveToFirst();
		Log.w("Stop" , busstop);
		Log.w("Distance" , cur.getCount() + "");
		return cur.getString(0);
	}

}
