package id.shobrun.myviewmodel.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import id.shobrun.myviewmodel.R;

import static android.view.Gravity.CENTER;

public class MyButton extends android.support.v7.widget.AppCompatButton {
    private Drawable enableBackground, disableBackground;
    private int textColor;

    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(isEnabled() ? enableBackground : disableBackground);
        setTextColor(textColor);
        setTextSize(12.f);
        setGravity(CENTER);
        setText(isEnabled() ? "Submit" : "Isi Dulu");
    }
    private void init(){
        textColor = ContextCompat.getColor(getContext(),android.R.color.background_light);
        enableBackground = getResources().getDrawable(R.drawable.bg_button);
        disableBackground = getResources().getDrawable(R.drawable.bg_button_disabled);

    }
}
