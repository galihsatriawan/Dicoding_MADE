package id.shobrun.mylistview;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import id.shobrun.mylistview.adapter.HeroAdapter;
import id.shobrun.mylistview.pojo.Hero;

public class MainActivity extends AppCompatActivity {
    ListView lvList ;
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private HeroAdapter adapter;
    private ArrayList<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvList = findViewById(R.id.lv_list);
        adapter = new HeroAdapter(this);
        lvList.setAdapter(adapter);
        prepare();
        addItem();
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,heroes.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    void prepare(){
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    void addItem(){
        heroes = new ArrayList<>();
        for(int i= 0 ;i< dataName.length;i++){
            Hero hero = new Hero();
            hero.setPhoto(dataPhoto.getResourceId(i,-1));
            hero.setName(dataName[i]);
            hero.setDescription(dataDescription[i]);
            heroes.add(hero);
        }
        adapter.setListHero(heroes);
    }
}
