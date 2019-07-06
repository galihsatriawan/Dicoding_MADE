package id.shobrun.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.shobrun.moviecatalogue.model.MainModel;
import id.shobrun.moviecatalogue.presenter.MainPresenter;
import id.shobrun.moviecatalogue.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainPresenter presenter = new MainPresenter(getApplicationContext(),this);
        presenter.loadMovie();
    }

    @Override
    public void showListMovieCatalogue(MainModel model) {

    }

    @Override
    public void showActionBar() {

    }
}
