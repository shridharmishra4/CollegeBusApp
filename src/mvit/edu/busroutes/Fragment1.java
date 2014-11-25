package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment1 extends Fragment {

	Spinner s;
	ListView l;
	List<String> list;
	String busStop = "Navarang Circle";
	String distance ;
	List<Bus> buses;
	Button click;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		View view = inflater.inflate(R.layout.fragment1, container, false);
		s = (Spinner) view.findViewById(R.id.f1_sp1);
		l = (ListView) view.findViewById(R.id.f1_lv1);
		click = (Button)view.findViewById(R.id.find);
		
		addspinner();
		setAdapter();
		
		return view;
	}
	
	




	private void addspinner() {
		DestinationDAO db = null;
		try {
			db = new DestinationDAO(getActivity());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.open();
		list = db.getdestinations();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, list);
		s.setAdapter(dataAdapter);
		addItemListener();
	}

	private void addItemListener() {
		s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				busStop = list.get(position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		
	}

	

	private class NewAdapter extends ArrayAdapter<Bus> {
		
		private ArrayList<Bus> objects;
		public NewAdapter(Context context, int resource, List<Bus> objects) {
			super(context, resource, objects);
			this.objects = (ArrayList<Bus>) objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = convertView;
			if (v == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.bus_adapter, null);
			}
			
			Bus bus = objects.get(position);
			TextView t1,t2,t3,t4,t5;
			t1 = (TextView)v.findViewById(R.id.busno);
			t2 = (TextView)v.findViewById(R.id.busno_tag);
			t3 = (TextView)v.findViewById(R.id.vechileno);
			t4 = (TextView)v.findViewById(R.id.vechileno_tag);
			t5 = (TextView)v.findViewById(R.id.f1_distance);
			t1.setText(bus.getBusNo() + "");
			t2.setText("Bus No");
			t3.setText(bus.VechileNo);
			t4.setText("Vechile No");
			t5.setText(distance + " KM");			
			return v ;
		}
		
		

		

	}
	
	public void setAdapter(){
		click.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try {
					BusDAO db = new BusDAO(getActivity());
					db.open();
					buses = db.getBusesByDestination(busStop);
					try {
						DistanceDAO db2 = new DistanceDAO(getActivity());
						db2.open();
						distance = db2.getDistanceOfStop(busStop);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				l.setAdapter(new NewAdapter(getActivity(),R.layout.bus_adapter,buses));
				
			}
			
		});
	}

}