package mvit.edu.busroutes;

import java.io.IOException;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentAdmin1 extends Fragment {
	TextView tv, name, designation, contact, gender, email;
	int[] pic = {R.drawable.shridhar , R.drawable.sid};
	ImageView iv ;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.adminfragment1, container, false);

		// for underlining the header!!
		tv = (TextView) view.findViewById(R.id.header);
		tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		tv.setText("DETAILS");
		Admin admin = (Admin) getActivity().getIntent().getExtras()
				.getSerializable("data");
		AdminDetail detail = null ;
		try{
			AdminDAO db = new AdminDAO(getActivity());
			db.open();
			detail = db.getAdminDetail(admin.getAdmin_name());
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		name = (TextView)view.findViewById(R.id.nameofadmin);
		designation = (TextView)view.findViewById(R.id.designation);
		contact = (TextView)view.findViewById(R.id.phone);
		gender = (TextView)view.findViewById(R.id.gender);
		email = (TextView)view.findViewById(R.id.email);
		iv = (ImageView)view.findViewById(R.id.imageView1);
		
		name.setText(admin.getAdmin_name());
		designation.setText(detail.getDesignation());
		contact.setText(detail.getPhone());
		gender.setText(detail.getGender());
		email.setText(detail.getEmail());
		
		if(admin.getAdmin_name().equals("Siddanth"))
			iv.setImageResource(pic[1]);
		else
			iv.setImageResource(pic[0]);
		
		return view;
	}

}
