package id.shobrun.moviecatalogue.views.fragment;

import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver;
import id.shobrun.moviecatalogue.views.MainActivity;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String RELEASE_REMINDER;
    private String DAILY_REMINDER;
    private ReminderReceiver reminderReceiver;

    private static final String timeDaily ="07:00";
    private static final String timeRelease ="08:00";
    private SwitchPreference isSetReleaseReminder,isSetDailyReminder;
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
        init();
        setSummaries();
    }
    private void init(){
        RELEASE_REMINDER = getResources().getString(R.string.key_release_reminder);
        DAILY_REMINDER = getResources().getString(R.string.key_daily_reminder);

        isSetDailyReminder = (SwitchPreference) findPreference(DAILY_REMINDER);
        isSetReleaseReminder = (SwitchPreference) findPreference(RELEASE_REMINDER);

        reminderReceiver = new ReminderReceiver();
    }

    private void setSummaries(){
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        isSetReleaseReminder.setChecked(sh.getBoolean(RELEASE_REMINDER,false));
        isSetDailyReminder.setChecked(sh.getBoolean(DAILY_REMINDER,false));
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if(key.equals(RELEASE_REMINDER)){
            isSetReleaseReminder.setChecked(sharedPreferences.getBoolean(RELEASE_REMINDER,false));
            boolean setRelease = sharedPreferences.getBoolean(RELEASE_REMINDER,false);
            if(setRelease){
                reminderReceiver.setRepeatAlarm(getContext(),ReminderReceiver.RELEASE_REMINDER,timeRelease);
                Toast.makeText(getContext(), "Set Release Reminder", Toast.LENGTH_SHORT).show();
            }else {
                reminderReceiver.cancelAlarm(getContext(),ReminderReceiver.RELEASE_REMINDER);
                Toast.makeText(getContext(), "Unset Release Reminder", Toast.LENGTH_SHORT).show();
            }
        }

        if (key.equals(DAILY_REMINDER)){
            isSetDailyReminder.setChecked(sharedPreferences.getBoolean(DAILY_REMINDER,false));
            boolean setDaily= sharedPreferences.getBoolean(DAILY_REMINDER,false);
            if(setDaily){
                String title = getContext().getResources().getString(R.string.daily_reminder);
                String message = getContext().getResources().getString(R.string.is_daily_reminder);

                reminderReceiver.setRepeatAlarm(getContext(),ReminderReceiver.DAILY_REMINDER,timeDaily,title,message,pendingIntent);
                Toast.makeText(getContext(), "Set Daily Reminder", Toast.LENGTH_SHORT).show();
            }else {
                reminderReceiver.cancelAlarm(getContext(),DAILY_REMINDER);
                Toast.makeText(getContext(), "Unset Daily Reminder", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
