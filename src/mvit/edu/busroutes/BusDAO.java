package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BusDAO {

	public SQLiteDatabase db;
	public DBHelper helper;
	public String[] allColumns = { "BUSNO", "ROUTENAME", "ROUTENO", "VECHILENO" };
	public final static String table = "BUS";

	public BusDAO(Context mycontext) throws IOException {
		helper = new DBHelper(mycontext);
		helper.createDataBase();
	}

	public void open() {
		db = helper.getReadableDatabase();
	}

	public void close() {
		helper.close();
	}

	// returns wether data was entered sucessfully or not!!
	public long addBus(Bus newbus) {
		ContentValues values = new ContentValues();
		values.put(allColumns[0], newbus.getBusNo());
		values.put(allColumns[1], newbus.getRouteName());
		values.put(allColumns[2], newbus.getRouteNo());
		values.put(allColumns[3], newbus.getVechileNo());

		return db.insert(table, null, values);
	}

	public List<Bus> getBuses() {
		List<Bus> buses = new ArrayList<Bus>();
		Cursor cur = db.rawQuery("Select * from BUS ;", null);
		if (cur.moveToFirst()) {
			do {
				buses.add(curToBus(cur));
			} while (cur.moveToNext());
		}

		return buses;
	}

	private Bus curToBus(Cursor cur) {

		Bus bus = new Bus();
		bus.setBusNo(cur.getLong(0));
		bus.setRouteName(cur.getString(1));
		bus.setRouteNo(cur.getLong(2));
		bus.setVechileNo(cur.getString(3));

		return bus;
	}

	public List<Bus> getBusesByDestination(String destination) {
		List<Bus> buses = new ArrayList<Bus>();
		Cursor cur = db
				.rawQuery(
						"select b.BUSNO , b.ROUTENAME , b.ROUTENO , B.VECHILENO from BUS b ,DESTINATION d " +
						"where b.BUSNO = d.BUSNO and d.DESTINATION = '"
								+ destination + "';", null);
		cur.moveToFirst();
		do{
			buses.add(curToBus(cur));
		}while(cur.moveToNext());

		return buses;
	}
	
	public List<String> getBusNos(){
		List<String> busno = new ArrayList<String>();
		Cursor cur = db.rawQuery("SELECT BUSNO FROM BUS ;", null);
		cur.moveToFirst();
		do{
			busno.add(cur.getString(0));
		}while(cur.moveToNext());
		return busno;
	}
	
	public Bus getBusByBusno(String busno){
		Cursor cur = db.rawQuery("SELECT * FROM BUS WHERE BUSNO = " + busno + ";", null);
		cur.moveToFirst();
		
		return curToBus(cur);
	}
	
	public String getNoOfStops(String busno){
		Cursor cur = db.rawQuery("select count(*) from bus,destination where " +
				"bus.busno = destination.busno and bus.busno = " + busno + ";", null);
		cur.moveToFirst();
		return cur.getString(0);
	}
	
}
