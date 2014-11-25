package mvit.edu.busroutes;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddDestination extends Activity{
	
	TextView name,bus , dis , time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddest);
		name = (TextView)findViewById(R.id.desttt_name);
		bus = (TextView)findViewById(R.id.dest_busno);
		dis = (TextView)findViewById(R.id.dest_distance);
		time = (TextView)findViewById(R.id.dest_time);
		
	}
	
	public void Clicked(View view){
		Long Busno = Long.parseLong(bus.getText().toString());
		Long Dist = Long.parseLong(dis.getText().toString());
		String DName = name.getText().toString();
		String Time = time.getText().toString();
		
		//bus name and time will go into destination
		try {
			DestinationDAO db = new DestinationDAO(this);
			db.open();
			db.addDestination(new Destination(Busno , DName , Time));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//destination and distance ill go into distance 
		try {
			DistanceDAO db = new DistanceDAO(this);
			db.open();
			db.addDistance(new Distance(DName , Dist));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Toast.makeText(getApplicationContext(), "Data Entered", Toast.LENGTH_SHORT).show();
		name.setText(" ");
		bus.setText(" ");
		dis.setText(" ");
		time.setText(" ");
	}

}
