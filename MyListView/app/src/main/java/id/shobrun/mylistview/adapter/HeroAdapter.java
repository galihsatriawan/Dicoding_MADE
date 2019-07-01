package id.shobrun.mylistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.shobrun.mylistview.R;
import id.shobrun.mylistview.pojo.Hero;


public class HeroAdapter extends BaseAdapter {
    public HeroAdapter(Context context) {
        this.context = context;
        this.listHero = new ArrayList<>();
    }

    private Context context ;
    private ArrayList<Hero> listHero;

    public ArrayList<Hero> getListHero() {
        return listHero;
    }

    public void setListHero(ArrayList<Hero> listHero) {
        this.listHero = listHero;
    }

    @Override
    public int getCount() {
        return getListHero().size();
    }

    @Override
    public Object getItem(int position) {
        return getListHero().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_hero,parent,false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Hero hero = (Hero)getItem(position);
        viewHolder.bind(hero);
        return view;
    }

    private class ViewHolder {
        private TextView txtName, txtDescription;
        private ImageView imgPhoto;
        public ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }
        void bind(Hero hero){
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}
