package com.ganjarramadhan.bookdoctor.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.ganjarramadhan.bookdoctor.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/12/16.
 */
public class TimeListAdapter extends ArrayAdapter<String> {

    List<Integer> unavailableTimeIndex;
    LayoutInflater inflater;
    int resource;
    String[] objects;
    Context context;
    Date selectedDate;
    Calendar today;

    public TimeListAdapter(Context context, int resource, String[] objects, List<Integer> unavailableTimeIndex, Date selectedDate) {
        super(context, resource, objects);
        this.unavailableTimeIndex = unavailableTimeIndex;
        this.resource = resource;
        this.objects = objects;
        this.context = context;
        this.selectedDate = selectedDate;
        this.today = Calendar.getInstance();
        inflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TimeListHolder holder;

        if (convertView == null){
            row = inflater.inflate(resource, parent, false);
            holder = new TimeListHolder();
            holder.checkedTextView = (CheckedTextView) row;
            row.setTag(holder);
        } else {
            holder = (TimeListHolder) row.getTag();
        }

        if (!isEnabled(position)) {
            holder.checkedTextView.setTextColor(ContextCompat.getColor(context, R.color.mdtp_date_picker_text_disabled));
        } else {
            holder.checkedTextView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }

        holder.checkedTextView.setText(objects[position]);

        return row;
    }

    @Override
    public boolean isEnabled(int position) {
        return !unavailableTimeIndex.contains(position) && checkTime(position);
    }

    private boolean checkTime(int position){
        long timeDiffInHour = (today.getTime().getTime() - selectedDate.getTime()) / (60 * 60 * 1000);
        if (timeDiffInHour > 24){
            return true;
        }

        // check hour today
        int now = today.get(Calendar.HOUR_OF_DAY);
        int dataHour = position + 9;
        if (now < dataHour){
            return true;
        }

        return false;
    }

    static class TimeListHolder {
        CheckedTextView checkedTextView;
    }
}
