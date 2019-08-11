package id.shobrun.moviecatalogue.presenters;

import android.content.Context;
import id.shobrun.moviecatalogue.views.MainView;

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
