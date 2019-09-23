package id.shobrun.consumer.views.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.shobrun.consumer.R;
import id.shobrun.consumer.views.fragment.ConsumerMovieFavoriteFragment;
import id.shobrun.consumer.views.fragment.MovieWishListFragment;

public class FavoriteSectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1,R.string.tab_text_3};
    private final Context mContext;

    public FavoriteSectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return ConsumerMovieFavoriteFragment.getInstance();
            default:
                return MovieWishListFragment.getInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
