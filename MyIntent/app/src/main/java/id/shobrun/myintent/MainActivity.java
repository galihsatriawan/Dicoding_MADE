package id.shobrun.myintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.shobrun.myintent.pojo.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMoveActivity,btnMoveWithDataActivity,btnMoveWithObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);

        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity.setOnClickListener(this);
        btnMoveWithObject.setOnClickListener(this);
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
        }
    }
}
