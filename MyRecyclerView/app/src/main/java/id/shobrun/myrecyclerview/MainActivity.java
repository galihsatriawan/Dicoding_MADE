package id.shobrun.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import id.shobrun.myrecyclerview.adapter.GridHeroAdapter;
import id.shobrun.myrecyclerview.adapter.ListHeroAdapter;
import id.shobrun.myrecyclerview.data.HeroesData;
import id.shobrun.myrecyclerview.pojo.Hero;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvHeroes;
    ArrayList<Hero> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeroes = findViewById(R.id.recyler_hero);
        rvHeroes.setHasFixedSize(true);
        prepare();
        showRecyclerList();

    }
    public void prepare(){
        datas.addAll(HeroesData.getListData());
    }
    public void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter adapter =new ListHeroAdapter(datas);
        rvHeroes.setAdapter(adapter);
    }
    public void showRecyclerGrid(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this,2));
        GridHeroAdapter adapter = new GridHeroAdapter(datas);
        rvHeroes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.action_list:{
                showRecyclerList();
                break;
            }
            case R.id.action_grid:{
                showRecyclerGrid();
                break;
            }
            case R.id.action_cardview:{

                break;
            }
        }
    }
}
