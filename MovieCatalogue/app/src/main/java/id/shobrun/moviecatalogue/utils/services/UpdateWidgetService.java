package id.shobrun.moviecatalogue.utils.services;

import android.app.IntentService;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.utils.widget.MovieFavoriteWidget;
import id.shobrun.moviecatalogue.utils.widget.StackRemoteViewsFactory;
import id.shobrun.moviecatalogue.viewmodels.DetailMovieViewModel;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class UpdateWidgetService extends JobService {

    private final String TAG = getClass().getSimpleName();


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: ");
        handleUpdateAppWidgets(params);
        return true;
    }
    private void stopJobUpdate(){
        JobScheduler stJob =(JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        stJob.cancel(DetailMovieViewModel.JOB_ID);
        Log.d(TAG, "stopJobUpdate: ");
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: ");
        return false;
    }

    private void handleUpdateAppWidgets(JobParameters parameters){
        Context context = getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, MovieFavoriteWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view);
        jobFinished(parameters,false);
        stopJobUpdate();
    }

}
