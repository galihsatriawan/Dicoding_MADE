package id.shobrun.moviecatalogue.utils.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import id.shobrun.moviecatalogue.utils.widget.StackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(getApplicationContext(),intent);
    }
}
