package mvit.edu.busroutes;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment4 extends Fragment {
	
	TextView uid , pass;
	Button login ;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment4, container,false);
			uid = (TextView)view.findViewById(R.id.f4_userid);
			pass = (TextView)view.findViewById(R.id.f4_pass);
			login = (Button)view.findViewById(R.id.f4_login);
			
			addListener();
		return view;
	}
	private void addListener() {
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String uid_text = uid.getText().toString();
				String pass_text = pass.getText().toString();
				String pass_ret ;
				uid.setText("");
				pass.setText("");
				
				try{
					AdminDAO db = new AdminDAO(getActivity());
					db.open();
					Admin admin = db.getAdmin(uid_text);
					pass_ret = admin.getAdmin_pass();
					Log.w("Pass" , pass_ret.length() + "");
					Log.w("Written_pass" , pass_text.length() + "");
					
					if (pass_text.equals(pass_ret)){
						Bundle basket = new Bundle();
						basket.putSerializable("data",admin );
						
						Intent i = new Intent(getActivity(),AdminActivity.class);
						i.putExtras(basket);
						startActivity(i);
						
					}
					
					else{
						AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
						adb.setTitle("Error").setMessage("Wrong UserName or Password").show();
					}
				}catch(CursorIndexOutOfBoundsException e){
					AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
					adb.setTitle("Error").setMessage("Wrong UserName or Password").show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}

}
