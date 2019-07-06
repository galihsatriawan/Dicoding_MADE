package id.shobrun.myunittesting.presenter;

import id.shobrun.myunittesting.model.MainModel;
import id.shobrun.myunittesting.view.MainView;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }
    double volume (double length,double width,double height){
        return length * width * height;
    }
    public void calculateVolume(double length,double width,double height){
        double volume = volume(length, width, height);
        MainModel mainModel = new MainModel(volume);
        view.showVolume(mainModel);
    }
}
