package mvit.edu.busroutes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FragmentAdmin2 extends Fragment {
	RadioGroup rg;
	Button btn;
	Intent i;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.adminfragment2, container, false);
		btn  = (Button)view.findViewById(R.id.update);
		rg = (RadioGroup)view.findViewById(R.id.rg1);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Log.w("In" , "There");
				switch (checkedId) {
				case R.id.af2_addbus:
						i = new Intent(getActivity(), AddBus.class);
					break;
				case R.id.af2_adddest:
						i = new Intent(getActivity(), AddDestination.class);
					break;
				}
			}
			
		});
		addListeners();
		return view;
	}

	

	private void addListeners() {
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(i);
			}

		});
	}

}
