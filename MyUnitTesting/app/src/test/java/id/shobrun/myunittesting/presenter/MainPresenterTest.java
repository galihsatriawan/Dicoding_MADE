package id.shobrun.myunittesting.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import id.shobrun.myunittesting.model.MainModel;
import id.shobrun.myunittesting.view.MainView;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    private MainPresenter mPresenter;
    private MainView mView;

    @Before
    public void setUp(){
        mView = mock(MainView.class);
        mPresenter = new MainPresenter(mView);
    }
    @Test
    public void testVolumeWithIntegerInput(){
        double volume = mPresenter.volume(2,8,1);
        assertEquals(16,volume,0.0001);
    }
    @Test
    public void testVolumeWithDoubleInput(){
        double volume = mPresenter.volume(2.3,8.1,2.9);
        assertEquals(54.026999999999994, volume, 0.0001);
    }
    @Test
    public void testVolumeWithNolInput(){
        double volume = mPresenter.volume(0,0,0);
        assertEquals(0.0001, volume ,0.0001);
    }
    @Test
    public void volume() {
    }

    @Test
    public void testCalculateVolume() {
        mPresenter.calculateVolume(11.1,2.2,1);
        verify(mView).showVolume(any(MainModel.class));
    }
}