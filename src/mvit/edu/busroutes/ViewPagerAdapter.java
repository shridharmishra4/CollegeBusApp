package mvit.edu.busroutes;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class ViewPagerAdapter extends FragmentPagerAdapter {
 
    final int PAGE_COUNT = 4;
    // Tab Titles
    private String tabtitles[] = new String[] { "WhichBusToRide", "BusInfo", "CrewInfo" , "AdminLogin" };
    Context context;
 
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
 
    @Override
    public Fragment getItem(int position) {
        switch (position) {
 
            // Open FragmentTab1.java
        case 0:
            Fragment1 fragmenttab1 = new Fragment1();
            return fragmenttab1;
 
            // Open FragmentTab2.java
        case 1:
            Fragment2 fragmenttab2 = new Fragment2();
            return fragmenttab2;
 
            // Open FragmentTab3.java
        case 2:
            Fragment3 fragmenttab3 = new Fragment3();
            return fragmenttab3;
            
        case 3:
            Fragment4 fragmenttab4 = new Fragment4();
            return fragmenttab4;
        }
        return null;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}