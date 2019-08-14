package id.shobrun.moviecatalogue.component.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.common.OnItemClickListener;
import id.shobrun.moviecatalogue.presenters.TVShowRecyclerPresenter;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowViewHolder> {

    TVShowRecyclerPresenter mPresenter;
    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

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
    public void onBindViewHolder(@NonNull TVShowViewHolder tvShowViewHolder,final int i) {
        tvShowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(v,i);
            }
        });
        mPresenter.onBindItemViewHolder(tvShowViewHolder,i);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getTvShowCount();
    }
}
