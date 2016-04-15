package com.ganjarramadhan.bookdoctor.module.history.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnUserListItemClick;
import com.ganjarramadhan.bookdoctor.module.history.view.listener.OnHistoryItemClickListener;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class PatientHistoryListAdapter extends RecyclerView.Adapter<PatientHistoryListAdapter.ViewHolderContent> {

    private List<Booking> listBooking;
    private OnHistoryItemClickListener onClickListener;
    private Activity activity;
    private String[] arrayOfTime;
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
    final Date current;

    public PatientHistoryListAdapter(Activity activity, List<Booking> listBooking,
                                     OnHistoryItemClickListener onClickListener){
        this.listBooking = listBooking;
        this.activity = activity;
        this.onClickListener = onClickListener;
        arrayOfTime = activity.getResources().getStringArray(R.array.time_array);
        current = new Date();
    }

    public void removeBookingObject(Booking booking){
        for (Booking bookingInList : listBooking){
            if (bookingInList.getId() == booking.getId()) {
                bookingInList.setStatus(booking.getStatus());
                break;
            } else {
                continue;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderContent onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_list, parent, false);
        return new ViewHolderContent(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderContent holder, int position) {

        holder.mItem = listBooking.get(position);
        Glide.with(activity).load(AppsConstant.BASE_DOCTOR_IMAGE_URL + holder.mItem.getDoctor().getAvatarUrl())
                .into(holder.mImgAvatar);
        holder.mNameView.setText(holder.mItem.getDoctor().getFirstName() + " " + holder.mItem.getDoctor().getLastName());
        holder.mLabel.setText("Booking Date: ");
        holder.mDateDay.setText(sdf.format(holder.mItem.getBookingDate()) + ". \nAt "
                + arrayOfTime[holder.mItem.getBookingTime()]);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClickListener){
                    onClickListener.onListBookingItemClicked(holder.mItem);
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(holder.mItem.getBookingDate());
        calendar.set(Calendar.HOUR_OF_DAY, position + 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        long timeDiff = current.getTime() - calendar.getTime().getTime();
        long diffInHour = timeDiff / (60 * 1000 * 60);
        Timber.d(holder.mItem.getDoctor().getFirstName() + ". Diff: " + timeDiff + ". Diff in Hour: " + diffInHour);
        if (diffInHour <= 24 && "active".equals(holder.mItem.getStatus())){
            holder.mBtnCancel.setVisibility(View.VISIBLE);
            holder.mBtnCancel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (null != onClickListener){
                        onClickListener.onBtnCancelBookingClicked(holder.mItem);
                    }
                }
            });
            holder.mStatus.setVisibility(View.GONE);
        } else {
            holder.mBtnCancel.setVisibility(View.GONE);
            holder.mStatus.setVisibility(View.VISIBLE);
            String status = "DONE";
            if (holder.mItem.getStatus().equals("canceled"))
                status = "CANCELED";
            holder.mStatus.setText(status);
        }

    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    public class ViewHolderContent extends RecyclerView.ViewHolder {
        public final View mView;
        public final CircleImageView mImgAvatar;
        public final TextView mNameView;
        public final TextView mLabel;
        public final TextView mDateDay;
        public final TextView mStatus;
        public final Button mBtnCancel;
        public Booking mItem;

        public ViewHolderContent(View view) {
            super(view);
            mView = view;
            mImgAvatar = (CircleImageView) view.findViewById(R.id.adapter_user_list_civ_avatar);
            mNameView = (TextView) view.findViewById(R.id.adapter_user_list_tv_name);
            mLabel = (TextView) view.findViewById(R.id.adapter_user_list_tv_label);
            mDateDay = (TextView) view.findViewById(R.id.adapter_user_list_tv_available_day);
            mStatus = (TextView) view.findViewById(R.id.adapter_user_list_tv_status);
            mBtnCancel = (Button) view.findViewById(R.id.adapter_user_list_btn_cancel);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
