package id.shobrun.myrecyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.shobrun.myrecyclerview.R;
import id.shobrun.myrecyclerview.helper.OnItemClickCallback;
import id.shobrun.myrecyclerview.pojo.Hero;

public class CardHeroAdapter extends RecyclerView.Adapter<CardHeroAdapter.CardViewHolder> {
    private ArrayList<Hero> heroes;

    public CardHeroAdapter(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_hero,viewGroup,false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int i) {
        Hero hero = heroes.get(i);
        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhotos);

        holder.tvName.setText(hero.getName());
        holder.tvFrom.setText(hero.getFrom());
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Share "+heroes.get(holder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Favorite "+heroes.get(holder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(heroes.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhotos;
        TextView tvName,tvFrom;
        Button btnShare,btnFavorite;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhotos = itemView.findViewById(R.id.image_item_photo);
            tvName = itemView.findViewById(R.id.text_item_name);
            tvFrom = itemView.findViewById(R.id.text_item_from);
            btnShare = itemView.findViewById(R.id.button_set_share);
            btnFavorite = itemView.findViewById(R.id.button_set_favorite);
        }
    }
}
