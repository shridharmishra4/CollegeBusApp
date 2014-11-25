package mvit.edu.busroutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment2 extends Fragment {

	Spinner s;
	TextView bno, bnot, routen, routent, vechn, vechnt, totstops, totstopst;
	Button btn, show;
	List<String> list;
	List<String> stoplist;
	List<String> timelist ;
	String busno;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		View view = inflater.inflate(R.layout.fragment2, container, false);
		s = (Spinner) view.findViewById(R.id.f2_spinner);
		btn = (Button) view.findViewById(R.id.btn3);
		show = (Button) view.findViewById(R.id.f2_show);

		addSpinner();
		addlistener();
		return view;
	}

	private void addlistener() {
		s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				busno = list.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					BusDAO db = new BusDAO(getActivity());
					db.open();
					Bus bus = db.getBusByBusno(busno);
					String stops = db.getNoOfStops(busno);
					bno.setText(bus.getRouteNo() + "");
					bnot.setText("Route No");
					routen.setText(bus.getRouteName());
					routent.setText("Route Name");
					vechn.setText(bus.getVechileNo());
					vechnt.setText("Vechile No");
					totstops.setText(stops);
					totstopst.setText("Total No Of Stops");
					show.setVisibility(0);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try {
					DestinationDAO db = new DestinationDAO(getActivity());
					db.open();
					stoplist = db.getdestinations(busno);
					timelist = db.getTime(busno);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
				adb.setTitle("Stops");
				/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getActivity(), android.R.layout.simple_spinner_item);
				for(int i = 0; i < stoplist.size() ; i++)
					adapter.add(stoplist.get(i));*/
				NewAdapter adapter = new NewAdapter(getActivity(),R.layout.stops,timelist);
				adb.setAdapter(adapter, null);
				adb.show();
				
			}

		});
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		bno = (TextView) view.findViewById(R.id.f2_busno);
		bnot = (TextView) view.findViewById(R.id.f2_busno_tag);
		routen = (TextView) view.findViewById(R.id.f2_routename);
		routent = (TextView) view.findViewById(R.id.f2_routename_tag);
		vechn = (TextView) view.findViewById(R.id.f2_vechileno);
		vechnt = (TextView) view.findViewById(R.id.f2_vechileno_tag);
		totstops = (TextView) view.findViewById(R.id.f2_noofStops);
		totstopst = (TextView) view.findViewById(R.id.f2_noofstops_tag);

	}

	private void addSpinner() {
		try {
			BusDAO db = new BusDAO(getActivity());
			db.open();
			list = db.getBusNos();
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_spinner_item, list);
			s.setAdapter(dataAdapter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private class NewAdapter extends ArrayAdapter<String> {
		
		private ArrayList<String> objects;
		@SuppressWarnings("unchecked")
		public NewAdapter(Context context, int resource, List<String> objects) {
			super(context, resource, objects);
			this.objects = (ArrayList<String>) objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = convertView;
			if (v == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.stops, null);
			}
			
					TextView a = (TextView)v.findViewById(R.id.stopname);
					TextView b = (TextView)v.findViewById(R.id.time);
					
					a.setText(stoplist.get(position));
					b.setText(timelist.get(position));
			return v ;
		}
		
		

		

	}

}