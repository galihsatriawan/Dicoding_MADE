package id.shobrun.myscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class GetCurrentWeatherJobService extends JobService {
    public static final String TAG  = GetCurrentWeatherJobService.class.getSimpleName();

    //Isikan dengan API Key dari openweathermap
    final String APP_ID ="35fe2d3ceee6b6164ead9eac50424a4f";

    final String CITY = "Malang";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: Executed");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Executed");
        return false;
    }

    private void getCurrentWeather(final JobParameters job){
        Log.d(TAG, "Running ");
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+CITY+"&appid="+APP_ID;
        Log.e(TAG, "getCurrentWeather: "+url );
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    String currentWeather = responseObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    String description = responseObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    double tempKelvin = responseObject.getJSONObject("main").getDouble("temp");

                    double tempCelcius = tempKelvin - 273;
                    String temperature = new DecimalFormat("##.##").format(tempCelcius);

                    String title = "Current Weather";
                    String message = currentWeather + ", " +description+" with"+temperature+" celcius";
                    int notifId = 100;

                    // Show Notification
                    showNotification(getApplicationContext(),title,message,notifId);
                    jobFinished(job,false);
                }catch (Exception e){
                    jobFinished(job,true);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Ketika gagal maka job harus di reschedule
                jobFinished(job,true);
            }
        });
    }
    private void showNotification(Context context,String title, String description , int notifId){

    }
}
