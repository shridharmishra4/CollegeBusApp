package mvit.edu.busroutes;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class AdminViewPagerAdapter extends FragmentPagerAdapter {
 
    final int PAGE_COUNT ;
    // Tab Titles
    private String tabtitles[] = new String[] { "AdminInfo" , "ThingsToBeDone" };
    Context context;
 
    public AdminViewPagerAdapter(FragmentManager fm,int cnt) {
        super(fm);
        PAGE_COUNT = cnt;
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
            FragmentAdmin1 fragmenttab1 = new FragmentAdmin1();
            return fragmenttab1;
 
            // Open FragmentTab2.java
        case 1:
            FragmentAdmin2 fragmenttab2 = new FragmentAdmin2();
            return fragmenttab2;
 
      
        }
        return null;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}