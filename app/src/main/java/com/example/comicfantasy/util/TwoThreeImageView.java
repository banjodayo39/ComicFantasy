package com.example.comicfantasy.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class TwoThreeImageView extends AppCompatImageView {

    int THREE=3;
    int TWO=2;
    /**
     * Creates a TwoThreeImageView
     *
     * @param context Used to talk to the UI and app resources
     */
    public TwoThreeImageView(Context context) {
        super(context);
    }

    public TwoThreeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TwoThreeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * This method measures the view and its content to determine the measured width and the measured
     * height, which will make 2:3 aspect ratio.
     *
     * @param widthMeasureSpec horizontal space requirements as imposed by the parent
     * @param heightMeasureSpec vertical space requirements as imposed by th parent
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int twoThreeHeight = MeasureSpec.getSize(widthMeasureSpec) * THREE / TWO;
        int twoThreeHeightSpec =
                MeasureSpec.makeMeasureSpec(twoThreeHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, twoThreeHeightSpec);
    }
}
