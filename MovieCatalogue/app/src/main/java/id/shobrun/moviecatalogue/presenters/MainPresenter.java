package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import id.shobrun.moviecatalogue.contracts.MainContract;


public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context ctx;
    public MainPresenter(Context ctx, MainContract.View mView) {
        this.mView = mView;
        this.ctx = ctx;
    }
    @Override
    public void onLoad() {
        mView.showTabLayout();
    }

}
