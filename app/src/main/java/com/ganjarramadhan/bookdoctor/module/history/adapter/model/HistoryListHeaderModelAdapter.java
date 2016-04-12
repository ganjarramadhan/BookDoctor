package com.ganjarramadhan.bookdoctor.module.history.adapter.model;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class HistoryListHeaderModelAdapter {

    private int iconHeaderResource;
    private String labelHeader;

    public HistoryListHeaderModelAdapter(int iconHeaderResource, String labelHeader) {
        this.iconHeaderResource = iconHeaderResource;
        this.labelHeader = labelHeader;
    }

    public int getIconHeaderResource() {
        return iconHeaderResource;
    }

    public void setIconHeaderResource(int iconHeaderResource) {
        this.iconHeaderResource = iconHeaderResource;
    }

    public String getLabelHeader() {
        return labelHeader;
    }

    public void setLabelHeader(String labelHeader) {
        this.labelHeader = labelHeader;
    }
}
