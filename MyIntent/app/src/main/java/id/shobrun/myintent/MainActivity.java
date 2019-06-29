package id.shobrun.myintent;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.shobrun.myintent.pojo.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMoveActivity,
            btnMoveWithDataActivity,
            btnMoveWithObject,
            btnDialPhone,
            btnMoveForResult;
    TextView tvResult;
    private int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            Button
         */
        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnDialPhone = findViewById(R.id.btn_dial_number);
        btnMoveForResult = findViewById(R.id.btn_move_for_result);

        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity.setOnClickListener(this);
        btnMoveWithObject.setOnClickListener(this);
        btnDialPhone.setOnClickListener(this);
        btnMoveForResult.setOnClickListener(this);

        /*
            TextView
         */
        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity :{
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            }
            case R.id.btn_move_activity_data:{
                Intent moveDataIntent = new Intent(MainActivity.this,MoveWIthDataActivity.class);
                moveDataIntent.putExtra(MoveWIthDataActivity.EXTRA_NAME,"Dicoding Galih Anggi Satriawan");
                moveDataIntent.putExtra(MoveWIthDataActivity.EXTRA_AGE,21);
                startActivity(moveDataIntent);
                break;
            }
            case R.id.btn_move_activity_object:{
                Person person = new Person();
                person.setName("Galih Anggi Satriawan");
                person.setAge(21);
                person.setEmail("satriawan.galih99@gmail.com");
                person.setCity("Malang");

                Intent moveWithObjectIntent = new Intent(MainActivity.this,MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON,person);
                startActivity(moveWithObjectIntent);
                break;
            }
            case R.id.btn_dial_number:{
                String phoneNumber = "087777861468";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            }
            case R.id.btn_move_for_result :{
                Intent moveForResultIntent = new Intent(MainActivity.this,MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent,REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
                tvResult.setText(String.format("Hasil : %s",selectedValue));
            }
        }
    }
}
