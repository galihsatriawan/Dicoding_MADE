package id.shobrun.moviecatalogue.contracts;

public interface MainContract {

    interface View extends RootView {
        void showTabLayout();
    }
    interface Presenter {
        void onLoad();
    }
}
