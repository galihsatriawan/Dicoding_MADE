package id.shobrun.moviecatalogue.component.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.common.OnViewClickListener;
import id.shobrun.moviecatalogue.view.MovieItemView;

public class MovieViewHolder extends RecyclerView.ViewHolder implements MovieItemView{
    private TextView tvTitle,tvExcerpt,tvRating;
    private ImageView imgPoster,imgWishlistOff,imgWishlistOn;
    private Button btnAddToWishlist;
    OnViewClickListener onViewClickListener;

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public MovieViewHolder(View view){
        super(view);
        tvTitle = view.findViewById(R.id.text_title);
        tvExcerpt = view.findViewById(R.id.text_excerpt);
        tvRating = view.findViewById(R.id.text_rating);
        imgPoster = view.findViewById(R.id.image_poster);
        imgWishlistOff = view.findViewById(R.id.image_wishlist_off);
        imgWishlistOn = view.findViewById(R.id.image_wishlist_on);
        btnAddToWishlist = view.findViewById(R.id.button_add_wishlist);
        btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClickListener.onViewClicked(imgWishlistOn,imgWishlistOff);
            }
        });
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setExcerpt(String excerpt) {
        String ex = excerpt.substring(0,17)+" [...]";
        tvExcerpt.setText(ex);
    }

    @Override
    public void setRating(double rating) {
        tvRating.setText(String.valueOf(rating));
    }

    @Override
    public void setPoster(int poster) {
        Glide.with(this.itemView.getContext()).load(poster).into(imgPoster);
    }

    @Override
    public void showView(View v) {
        v.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideView(View v) {
        v.setVisibility(View.GONE);
    }
}
