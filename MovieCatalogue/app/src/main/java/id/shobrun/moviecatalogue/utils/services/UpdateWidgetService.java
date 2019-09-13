package id.shobrun.moviecatalogue.utils.services;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import id.shobrun.moviecatalogue.utils.widget.MovieFavoriteWidget;
import id.shobrun.moviecatalogue.utils.widget.StackRemoteViewsFactory;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class UpdateWidgetService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private final String TAG = getClass().getSimpleName();
    public static final String ACTION_UPDATE_WIDGETS= "id.shobrun.moviecatalogue.utils.services.action.UPDATE_WIDGETS";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "id.shobrun.moviecatalogue.utils.services.extra.PARAM1";

    public UpdateWidgetService() {
        super("UpdateWidgetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_WIDGETS.equals(action)) {
                Log.d(TAG, "onHandleIntent: ");
                handleUpdateAppWidgets();
            }
        }
    }

    private void handleUpdateAppWidgets(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this.getApplicationContext(), MovieFavoriteWidget.class));

        MovieFavoriteWidget.updateAllWidget(this,appWidgetManager,appWidgetIds);

    }

    public static void startActionUpdateAppWidgets(Context context){
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.setAction(ACTION_UPDATE_WIDGETS);
        context.startService(intent);
    }
}
