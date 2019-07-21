package id.shobrun.moviecatalogue.component;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.view.MovieItemView;

public class MovieViewHolder extends RecyclerView.ViewHolder implements MovieItemView {
    private TextView tvTitle,tvExcerpt,tvRating;
    private ImageView imgPoster,imgWishlistOff,imgWishlistOn;
    private Button btnAddToWishlist;
    public MovieViewHolder(View view){
        super(view);
        tvTitle = view.findViewById(R.id.text_title);
        tvExcerpt = view.findViewById(R.id.text_excerpt);
        tvRating = view.findViewById(R.id.text_rating);
        imgPoster = view.findViewById(R.id.image_poster);
        imgWishlistOff = view.findViewById(R.id.image_wishlist_off);
        imgWishlistOn = view.findViewById(R.id.image_wishlist_on);
        btnAddToWishlist = view.findViewById(R.id.button_add_wishlist);
    }
    void bind(final Movie movie){

        btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imgWishlistOff.getVisibility()==View.VISIBLE){
                    imgWishlistOn.setVisibility(View.VISIBLE);
                    imgWishlistOff.setVisibility(View.GONE);
                    Toast.makeText(v.getContext(),movie.getName()+" has added",Toast.LENGTH_SHORT).show();
                }else{
                    imgWishlistOn.setVisibility(View.GONE);
                    imgWishlistOff.setVisibility(View.VISIBLE);
                    Toast.makeText(v.getContext(),movie.getName()+" has removed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setExcerpt(String excerpt) {
        tvExcerpt.setText(excerpt.substring(0,17)+" [...]");
    }

    @Override
    public void setRating(double rating) {
        tvRating.setText(rating+"");
    }

    @Override
    public void setPoster(int poster) {
        Glide.with(this.itemView.getContext()).load(poster).into(imgPoster);
    }
}
