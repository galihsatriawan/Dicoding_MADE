package id.shobrun.moviecatalogue.views.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import id.shobrun.moviecatalogue.R;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String RELEASE_REMINDER;
    private String DAILY_REMINDER;

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
        if(key.equals(RELEASE_REMINDER)){
            isSetReleaseReminder.setChecked(sharedPreferences.getBoolean(RELEASE_REMINDER,false));
            boolean setRelease = sharedPreferences.getBoolean(RELEASE_REMINDER,false);
            if(setRelease){
                Toast.makeText(getContext(), "Set Release Reminder", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Unset Release Reminder", Toast.LENGTH_SHORT).show();
            }
        }

        if (key.equals(DAILY_REMINDER)){
            isSetDailyReminder.setChecked(sharedPreferences.getBoolean(DAILY_REMINDER,false));
            boolean setDaily= sharedPreferences.getBoolean(DAILY_REMINDER,false);
            if(setDaily){
                Toast.makeText(getContext(), "Set Daily Reminder", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Unset Daily Reminder", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
