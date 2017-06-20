package nhn.calendarapp.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by nguyennguyen on 20/6/17.
 */

public class SwipeButton extends RelativeLayout {

    private ImageView slidingButton;
    private float initialX;
    private boolean active;
    private int initialButtonWidth;
    private TextView textView;

    public SwipeButton(Context context) {
        super(context);
    }

    public SwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
