package id.shobrun.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lvList ;
    private String[] dataName = {"Cut Nyak Dien","Ki Hajar Dewantara"
            ,"Moh Yamin", "Patimura","R A Kartini","Sukarno"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvList = findViewById(R.id.lv_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,android.R.id.text1,dataName);
        lvList.setAdapter(adapter);
    }
}
