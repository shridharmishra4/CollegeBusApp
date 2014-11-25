package mvit.edu.busroutes;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddBus extends Activity{
	TextView busno,rouno,rouna ,vechno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbus);
		busno = (TextView)findViewById(R.id.editText1);
		rouno = (TextView)findViewById(R.id.dest_busno);
		rouna = (TextView)findViewById(R.id.dest_time);
		vechno = (TextView)findViewById(R.id.dest_distance);
	}
	
	public void clicked(View view){
		String busNo = busno.getText().toString();
		String rouNo = rouno.getText().toString();
		String rouNa = rouna.getText().toString();
		String vechNo = vechno.getText().toString();
		
		Bus bus = new Bus(Long.parseLong(busNo) , rouNa , Long.parseLong(rouNo) , vechNo);
		try {
			BusDAO db = new BusDAO(getApplicationContext());
			db.open();
			db.addBus(bus);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		busno.setText(" ");
		rouno.setText(" ");
		rouna.setText(" ");
		vechno.setText(" ");
		Toast.makeText(getApplicationContext(), "Data Entered", Toast.LENGTH_SHORT).show();
	}

}
