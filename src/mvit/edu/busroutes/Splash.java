package mvit.edu.busroutes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends Activity {
	ProgressBar pg ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		pg = (ProgressBar)findViewById(R.id.pB);
		pg.setMax(5);
		
		
			Thread timer = new Thread(){
				public void run(){
					int val = 1;
					while(val < 6){
						pg.setProgress(val);
						val++;
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					Intent i = new Intent(Splash.this, MainActivity.class);
					startActivity(i);
				}
			};
		
			
			timer.start();
			
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

}
