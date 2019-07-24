package id.shobrun.moviecatalogue.component.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.presenter.TVShowRecyclerPresenter;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowViewHolder> {
    TVShowRecyclerPresenter mPresenter;

    public TVShowAdapter(TVShowRecyclerPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TVShowViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_tv_show,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder tvShowViewHolder, int i) {
        mPresenter.onBindItemViewHolder(tvShowViewHolder,i);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getMovieCount();
    }
}
