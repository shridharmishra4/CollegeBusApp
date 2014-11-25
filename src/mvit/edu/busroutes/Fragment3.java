package mvit.edu.busroutes ;
import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
 
public class Fragment3 extends Fragment {
	
	Spinner s;
	Button btn;
	List<String> list;
	String busno;
	TextView driverna ,drivernat, driverno , drivernot; 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.fragment3, container, false);
        s = (Spinner) view.findViewById(R.id.f3_spinner);
		btn = (Button) view.findViewById(R.id.btn4);
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
					CrewDAO db = new CrewDAO(getActivity());
					db.open();
					Crew crew = db.getCrewByBusNo(busno);
					driverna.setText(crew.getDriverName());
					drivernat.setText("Driver Name");
					driverno.setText(crew.getDriverPhno());
					drivernot.setText("Driver PhNo");

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}
    
    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		driverna = (TextView)view.findViewById(R.id.f3_drivername);
		drivernat = (TextView)view.findViewById(R.id.f3_drivername_tag);
		driverno = (TextView)view.findViewById(R.id.f3_driverno);
		drivernot = (TextView)view.findViewById(R.id.f3_driverno_tag);
		

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
    
 
}