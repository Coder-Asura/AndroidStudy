package com.asura.greendao_study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asura.greendao_study.R;
import com.asura.greendao_study.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liuxd on 2016/9/8 10:58.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context context, List<User> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.tv_user.setText(mUsers.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    static class UserHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user)
        public TextView tv_user;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
