package com.ganjarramadhan.bookdoctor.module.dashboard.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.history.adapter.model.HistoryListAdapterModel;
import com.ganjarramadhan.bookdoctor.module.history.view.listener.OnHistoryItemClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int LIST_HEADER = 1;
    public static final int LIST_CONTENT = 2;

    public static final int LIST_CUSTOMER = 1;
    public static final int LIST_DOCTOR = 2;

    private List<HistoryListAdapterModel> listBooking;
    private OnHistoryItemClickListener onClickListener;
    private Activity activity;
    private int listOwner;

    public HistoryListAdapter(int listOwner, Activity activity, List<HistoryListAdapterModel> listBooking,
                              OnHistoryItemClickListener onClickListener){
        this.listOwner = listOwner;
        this.listBooking = listBooking;
        this.activity = activity;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == LIST_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list_header, parent, false);
            return  new ViewHolderHeader(v);
        }
        else if(viewType == LIST_CONTENT)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
            return new ViewHolderContent(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final HistoryListAdapterModel historyListAdapterModel = listBooking.get(position);

        if(holder instanceof ViewHolderHeader) {

            ViewHolderHeader VHHeader = (ViewHolderHeader)holder;
            VHHeader.mLabel.setText(historyListAdapterModel.getHeaderData().getLabelHeader());
            VHHeader.mImgIcon.setImageResource(historyListAdapterModel.getHeaderData().getIconHeaderResource());

        } else if(holder instanceof ViewHolderContent){
            ViewHolderContent VHContent = (ViewHolderContent) holder;

            if (listOwner == LIST_DOCTOR) {
                Glide.with(activity).load(historyListAdapterModel.getBookingData().getDoctor()
                        .getAvatarUrl()).into(((ViewHolderContent) holder).mImgAvatar);
                VHContent.mNameView.setText(historyListAdapterModel.getBookingData().getDoctor().getFullName());
            } else {
                Glide.with(activity).load(historyListAdapterModel.getBookingData().getCustomer()
                        .getAvatarUrl()).into(((ViewHolderContent) holder).mImgAvatar);
                VHContent.mNameView.setText(historyListAdapterModel.getBookingData().getCustomer().getFullName());
            }

            VHContent.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onClickListener){
                        onClickListener.onListBookingItemClicked(historyListAdapterModel.getBookingData());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listBooking.get(position).getListType();
    }

    public class ViewHolderHeader extends RecyclerView.ViewHolder {
        public final View mView;
        public final CircleImageView mImgIcon;
        public final TextView mLabel;

        public ViewHolderHeader(View view) {
            super(view);
            mView = view;
            mImgIcon = (CircleImageView) view.findViewById(R.id.adapter_user_list_civ_header_icon);
            mLabel = (TextView) view.findViewById(R.id.adapter_user_list_tv_label);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mLabel.getText() + "'";
        }
    }

    public class ViewHolderContent extends RecyclerView.ViewHolder {
        public final View mView;
        public final CircleImageView mImgAvatar;
        public final TextView mNameView;

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
