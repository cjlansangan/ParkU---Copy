package parku.com.au.parku.view.models;

import parku.com.au.parku.R;

/**
 * Created by Celdane.Lansangan on 2/16/2016.
 */
public enum StepsViewPagerEnum {
    STEP1(R.string.str_step1_view, R.layout.content_instructions_step1),
    STEP2(R.string.str_step2_view, R.layout.content_instructions_step2),
    STEP3(R.string.str_step3_view, R.layout.content_instructions_step3);

    private int mTitleResId;
    private int mLayoutRestId;

    StepsViewPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutRestId = layoutResId;
    }

    public int getmTitleResId() {
        return mTitleResId;
    }

    public void setmTitleResId(int mTitleResId) {
        this.mTitleResId = mTitleResId;
    }

    public int getmLayoutRestId() {
        return mLayoutRestId;
    }

    public void setmLayoutRestId(int mLayoutRestId) {
        this.mLayoutRestId = mLayoutRestId;
    }
}
