package id.shobrun.learnespresso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtText;
    TextView tvPrinted;
    Button btnSetText, btnMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Learn Espresso");
        }
        edtText = findViewById(R.id.edt_text);
        tvPrinted = findViewById(R.id.text_printed_text);
        btnSetText = findViewById(R.id.button_change_text);
        btnMove = findViewById(R.id.button_switch_activity);
        btnMove.setOnClickListener(this);
        btnSetText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_change_text:
                String input = edtText.getText().toString().trim();
                tvPrinted.setText(input);
                break;
            case R.id.button_switch_activity:
                String text = edtText.getText().toString().trim();
                Intent move  = new Intent(MainActivity.this,SecondActivity.class);
                move.putExtra(SecondActivity.EXTRA_DATA,text);
                startActivity(move);
                break;
        }
    }
}
