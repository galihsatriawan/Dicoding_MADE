package id.shobrun.moviecatalogue.component;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.data.Movie;

public class MovieViewHolder {
    private Context context;
    private TextView tvTitle,tvExcerpt,tvRating;
    private ImageView imgPoster,imgWishlistOff,imgWishlistOn;
    private Button btnAddToWishlist;
    MovieViewHolder(View view){
        context = view.getContext();
        tvTitle = view.findViewById(R.id.text_title);
        tvExcerpt = view.findViewById(R.id.text_excerpt);
        tvRating = view.findViewById(R.id.text_rating);
        imgPoster = view.findViewById(R.id.image_poster);
        imgWishlistOff = view.findViewById(R.id.image_wishlist_off);
        imgWishlistOn = view.findViewById(R.id.image_wishlist_on);
        btnAddToWishlist = view.findViewById(R.id.button_add_wishlist);
    }
    void bind(Movie movie){
        tvTitle.setText(movie.getName());
        tvExcerpt.setText(movie.getDescription().substring(0,25)+" ...");
        tvRating.setText(movie.getRating()+"");
        Glide.with(context).load(movie.getPoster()).into(imgPoster);
        btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgWishlistOff.getVisibility()==View.VISIBLE){
                    imgWishlistOn.setVisibility(View.VISIBLE);
                    imgWishlistOff.setVisibility(View.GONE);
                }else{
                    imgWishlistOn.setVisibility(View.GONE);
                    imgWishlistOff.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
