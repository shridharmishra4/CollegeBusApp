package mvit.edu.busroutes;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class AdminActivity extends FragmentActivity {
	int flag = 1;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.admin_activity);
		Admin admin = (Admin) getIntent().getExtras()
				.getSerializable("data");
		AdminDetail detail = null ;
		try{
			AdminDAO db = new AdminDAO(this);
			db.open();
			detail = db.getAdminDetail(admin.getAdmin_name());
			if (detail.getDesignation().equals("Manager"))
				flag = 2;
		}catch(IOException e){
			e.printStackTrace();
		}
		// Locate the viewpager in activity_main.xml
		ViewPager viewPager = (ViewPager) findViewById(R.id.adminpager);

		// Set the ViewPagerAdapter into ViewPager
		viewPager.setAdapter(new AdminViewPagerAdapter(
				getSupportFragmentManager(),flag) );

	}
}
