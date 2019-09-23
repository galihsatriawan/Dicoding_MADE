package id.shobrun.consumer.utils.common;

import android.view.View;

public interface OnViewClickListener {
    void onViewClicked(View view);
    void onViewClicked(View v1,View v2);
    <T> void  onViewClicked(T obj,View v1,View v2);
}
