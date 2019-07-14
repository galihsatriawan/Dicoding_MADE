package id.shobrun.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import id.shobrun.myrecyclerview.adapter.CardHeroAdapter;
import id.shobrun.myrecyclerview.adapter.GridHeroAdapter;
import id.shobrun.myrecyclerview.adapter.ListHeroAdapter;
import id.shobrun.myrecyclerview.data.HeroesData;
import id.shobrun.myrecyclerview.helper.OnItemClickCallback;
import id.shobrun.myrecyclerview.pojo.Hero;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvHeroes;
    ArrayList<Hero> datas = new ArrayList<>();
    public final String EXTRA_TITLE ="extra_title";
    public final String EXTRA_LIST ="extra_list";
    public final String EXTRA_MODE = "extra_mode";
    private String title = "Mode List";
    private int mode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHeroes = findViewById(R.id.recyler_hero);
        rvHeroes.setHasFixedSize(true);
        if(savedInstanceState == null){
            prepare();
            showRecyclerList();
            mode = R.id.action_list;
        }else{
            ArrayList<Hero> stateList = savedInstanceState.getParcelableArrayList(EXTRA_LIST);
            mode = savedInstanceState.getInt(EXTRA_MODE);

            if(stateList!= null){
                datas.addAll(stateList);
            }
            setMode(mode);

        }



    }
    public void prepare(){
        datas.addAll(HeroesData.getListData());
    }
    public void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter adapter =new ListHeroAdapter(datas);
        rvHeroes.setAdapter(adapter);
        adapter.setOnItemClickCallback(new OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero hero) {
                showSelectedHero(hero);
            }
        });

    }
    public void showRecyclerGrid(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this,2));
        GridHeroAdapter adapter = new GridHeroAdapter(datas);
        rvHeroes.setAdapter(adapter);
        adapter.setOnItemClickCallback(new OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero hero) {
                showSelectedHero(hero);
            }
        });
    }
    public void showRecylcerCard(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardHeroAdapter adapter = new CardHeroAdapter(datas);
        rvHeroes.setAdapter(adapter);
        adapter.setOnItemClickCallback(new OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero hero) {
                showSelectedHero(hero);
            }
        });
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
                title = "Mode List";
                showRecyclerList();
                break;
            }
            case R.id.action_grid:{
                title = "Mode Grid";
                showRecyclerGrid();
                break;
            }
            case R.id.action_cardview:{
                title = "Mode CardView";
                showRecylcerCard();
                break;
            }
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }
    public void setActionBarTitle(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE,title);
        outState.putParcelableArrayList(EXTRA_LIST,datas);
        outState.putInt(EXTRA_MODE,mode);
    }
    public void showSelectedHero(Hero hero){
        Toast.makeText(getApplicationContext(),"Kamu Memilih "+hero.getName(),Toast.LENGTH_SHORT).show();
    }
}
