package id.shobrun.myviewmodel.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import id.shobrun.myviewmodel.R;



public class MySearchView extends android.support.v7.widget.AppCompatEditText {
    Drawable mSearchButtonImage;
    public MySearchView(Context context) {
        super(context);
        init();
    }

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Apa yang ingin anda Cari?");
        setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
    }
    private void init(){
        mSearchButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_search_black_24dp,null);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    showSearchButton();
                }else{
                    hideSearchButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void showSearchButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,mSearchButtonImage,null);
    }
    private void hideSearchButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
    }
}
