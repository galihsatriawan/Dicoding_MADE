package id.shobrun.mytestingapp;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSetValue;
    private TextView tvText;
    private ArrayList<String> names;
    private DelayAsync delayAsync;
    private ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSetValue = findViewById(R.id.btn_set_value);
        tvText = findViewById(R.id.tv_text);
        imgPreview = findViewById(R.id.img_preview);

        btnSetValue.setOnClickListener(this);
//        imgPreview.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.fronalpstock_big));
        Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview);
        names = new ArrayList<>();
        names.add("Galih Anggi Satriawan");
        names.add("Narenda Wicaksono");
        names.add("Kevin");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set_value:{
                StringBuilder name = new StringBuilder();
                for(int i = 0 ; i < names.size();i++){
                    name.append(names.get(i)).append("\n");
                }
                tvText.setText(name.toString());
                delayAsync = new DelayAsync();
                delayAsync.execute();
                break;
            }
        }
    }
    private static class DelayAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(3000000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d( "DelayAsync: ","Done");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("DelayAsync: ","Cancelled");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(delayAsync != null){
            if(delayAsync.getStatus().equals(AsyncTask.Status.RUNNING)){
                delayAsync.cancel(true);
            }
        }
    }
}
