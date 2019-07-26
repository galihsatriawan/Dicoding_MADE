package id.shobrun.myservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStartService;
    Button btnStartIntentService;
    Button btnStartBoundService;
    Button btnStopBoundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(this);

        btnStartIntentService = findViewById(R.id.btn_start_intent_service);
        btnStartIntentService.setOnClickListener(this);

        btnStartBoundService = findViewById(R.id.btn_start_bound_service);
        btnStartBoundService.setOnClickListener(this);

        btnStopBoundService = findViewById(R.id.btn_stop_bound_service);
        btnStopBoundService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_service:

                break;
            case R.id.btn_start_bound_service:

                break;
            case R.id.btn_start_intent_service:

                break;
            case R.id.btn_stop_bound_service:

                break;

        }
    }
}
