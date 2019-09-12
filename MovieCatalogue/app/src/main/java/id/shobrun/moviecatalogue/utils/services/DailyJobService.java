package id.shobrun.moviecatalogue.utils.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.utils.Notifications;
import id.shobrun.moviecatalogue.views.MainActivity;

public class DailyJobService extends JobService {
    public static int DAILY_JOB = 12;
    private int notifId = 12;
    @Override
    public boolean onStartJob(JobParameters params) {
        Context context = getApplicationContext();
        String title = context.getResources().getString(R.string.app_name);
        String message = context.getResources().getString(R.string.app_name);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        Notifications.showNotification(context,title,message,notifId,pendingIntent);

        jobFinished(params,false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
    /*
    public void startJobDaily(){
        ComponentName name = new ComponentName(getActivity(), DailyJobService.class);
        JobInfo.Builder builder= new JobInfo.Builder(DailyJobService.DAILY_JOB,name);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        builder.setRequiresCharging(false);
        builder.setRequiresDeviceIdle(false);

        JobScheduler jobScheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
    */

}
