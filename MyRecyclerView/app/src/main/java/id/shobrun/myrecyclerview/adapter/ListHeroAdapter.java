package id.shobrun.myrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import id.shobrun.myrecyclerview.R;
import id.shobrun.myrecyclerview.helper.OnItemClickCallback;
import id.shobrun.myrecyclerview.pojo.Hero;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHeroes;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListHeroAdapter(Context context) {
        this.context = context;
    }
    public ListHeroAdapter(ArrayList<Hero> listHeroes) {
        this.listHeroes = listHeroes;
    }

    private Context context;
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_hero,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final Hero hero = listHeroes.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);
        holder.tvNames.setText(hero.getName());
        holder.tvFrom.setText(hero.getFrom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listHeroes.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHeroes.size();
    }

    public void setListHeroes(ArrayList<Hero> listHeroes) {
        this.listHeroes = listHeroes;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvNames,tvFrom;
        ImageView imgPhoto;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNames = itemView.findViewById(R.id.text_item_name);
            tvFrom = itemView.findViewById(R.id.text_item_from);
            imgPhoto = itemView.findViewById(R.id.image_item_photo);
        }
    }

}

