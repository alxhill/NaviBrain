package com.lemonslice.navibrain;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String APPTAG = "NAVIBRAIN";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static String[] brainLobes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // gets the brain lobes list in /res/values/strings.xml
        brainLobes = getResources().getStringArray(R.array.brain_lobes);

        /// --- android boilerplate stuff --- ///
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, BrainFragment.getInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        mTitle = brainLobes[number];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BrainFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Sparse array (as in an array that isn't always full) to cache the BrainFragments such
         * that they're not requested every time the user changes view.
         */
        private static SparseArray<BrainFragment> brainFragmentCache = new SparseArray<BrainFragment>(4);

        /**
         * Return a BrainFragment for the given sectionNumber, filling the cache if empty
         * or returning the cached version if not.
         * @param sectionNumber
         * @return
         */
        public static BrainFragment getInstance(int sectionNumber) {
            if (brainFragmentCache.get(sectionNumber) == null) {
                brainFragmentCache.put(sectionNumber, newInstance(sectionNumber));
            }
            return brainFragmentCache.get(sectionNumber);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static BrainFragment newInstance(int sectionNumber) {
            BrainFragment fragment = new BrainFragment();
            Bundle args = new Bundle();

            // send the section number to the fragment when it's created.
            args.putInt(ARG_SECTION_NUMBER, sectionNumber-1);

            fragment.setArguments(args);

            return fragment;
        }

        public BrainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // get a reference to the views in the fragment
            TextView brainDescription = (TextView) rootView.findViewById(R.id.brain_description);
            ImageView brainImage = (ImageView) rootView.findViewById(R.id.brain_image);

            // get the section to display using the brainLobes data from the outer class,
            // and the section number argument from newInstance.
            String brainSection = brainLobes[getArguments().getInt(ARG_SECTION_NUMBER)];
            BrainData data = BrainData.getBrainData(brainSection);

            // update the views of the fragment so it shows brain data for the correct section
            // see BrainData.java to understand where the data is
            brainDescription.setText(data.getDescription());
            brainImage.setImageResource(data.getImageResource());

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            // sends the section number to the main view so it can update the title, see outer class
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
