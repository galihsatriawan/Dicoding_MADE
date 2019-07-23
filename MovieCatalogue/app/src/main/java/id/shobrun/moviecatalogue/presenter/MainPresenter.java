package id.shobrun.moviecatalogue.presenter;

import android.content.Context;
import id.shobrun.moviecatalogue.view.MainView;

public class MainPresenter {
    private MainView mView;
    private Context ctx;
    public MainPresenter(Context ctx, MainView mView) {
        this.mView = mView;
        this.ctx = ctx;
    }
    public void onLoad() {
        mView.showTabLayout();
    }

}
