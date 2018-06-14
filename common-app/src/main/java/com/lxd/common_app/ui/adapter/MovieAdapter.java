package com.lxd.common_app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxd.common_app.R;
import com.lxd.common_app.model.MovieEntity;
import com.lxd.common_app.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liuxd on 2016/10/25 14:57.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context mContext;
    private MovieEntity mMovieEntity;
    OnItemClickListener mOnItemClickListener;

    public MovieAdapter(Context context) {
        this.mContext = context;
    }

    public void setMovieEntity(MovieEntity movieEntity, boolean loadMore) {
        if (loadMore) {
            this.mMovieEntity.getSubjects().addAll(movieEntity.getSubjects());
        } else {
            this.mMovieEntity = movieEntity;
        }
        notifyDataSetChanged();
    }

    public MovieEntity getMovieEntity() {
        return mMovieEntity;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        if (mMovieEntity != null) {
//            ImageLoadUtil.getInstance().load(mContext, mMovieEntity.getSubjects().get(position).getImages().getLarge(), holder.mMoviePic);
//            Glide.with(mContext).load(mMovieEntity.getSubjects().get(position).getImages().getMedium())
//                    .centerCrop().into(holder.mMoviePic);
            holder.mMovieName.setText(mMovieEntity.getSubjects().get(position).getTitle());
            holder.mMovieDesc.setText(mMovieEntity.getSubjects().get(position).getOriginal_title());
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.i("点了" + holder.getAdapterPosition());

                        mOnItemClickListener.onItemClickListener(v, holder.getAdapterPosition());
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return mMovieEntity == null ? 0 : mMovieEntity.getSubjects().size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_pic)
        ImageView mMoviePic;
        @BindView(R.id.movie_name)
        TextView mMovieName;
        @BindView(R.id.movie_desc)
        TextView mMovieDesc;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }
}
