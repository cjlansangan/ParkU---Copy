package parku.com.au.parku.view.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import parku.com.au.parku.view.models.StepsViewPagerEnum;

/**
 * Created by Celdane.Lansangan on 2/16/2016.
 */
public class StepsViewPageAdapters extends PagerAdapter{
    private Context mContext;


    // constructor
    public StepsViewPageAdapters(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        StepsViewPagerEnum stepsViewPagerEnum = StepsViewPagerEnum.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup)inflater.inflate(stepsViewPagerEnum.getmLayoutRestId(), container, false);
        container.addView(layout);

        return layout;
    }

    @Override
    public int getCount() {
        return StepsViewPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}