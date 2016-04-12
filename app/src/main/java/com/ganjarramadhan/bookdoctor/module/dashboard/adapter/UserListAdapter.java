package com.ganjarramadhan.bookdoctor.module.dashboard.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnUserListItemClick;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolderContent> {

    private List<User> listUser;
    private OnUserListItemClick onClickListener;
    private Activity activity;

    public UserListAdapter(Activity activity, List<User> listUser,
                           OnUserListItemClick onClickListener){
        this.listUser = listUser;
        this.activity = activity;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolderContent onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_list, parent, false);
        return new ViewHolderContent(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderContent holder, int position) {

        holder.mItem = listUser.get(position);

        Glide.with(activity).load(holder.mItem.getAvatarUrl())
                .into(holder.mImgAvatar);
        holder.mNameView.setText(holder.mItem.getFullName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClickListener){
                    onClickListener.onUserItemClick(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolderContent extends RecyclerView.ViewHolder {
        public final View mView;
        public final CircleImageView mImgAvatar;
        public final TextView mNameView;
        public User mItem;

        public ViewHolderContent(View view) {
            super(view);
            mView = view;
            mImgAvatar = (CircleImageView) view.findViewById(R.id.adapter_user_list_civ_avatar);
            mNameView = (TextView) view.findViewById(R.id.adapter_user_list_tv_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
