package id.shobrun.moviecatalogue.presenter;

import android.content.Context;
import android.support.annotation.StringRes;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.view.MainView;

public class MainPresenter {


    private MainView mView;

    public MainPresenter(Context ctx, MainView mView) {
        this.mView = mView;
        Context ctx1 = ctx;
    }
    public void onLoad() {
        mView.showTabLayout();
    }

}
