package com.onedio.androidside.mvp.model.dashboard.onedioFragment.dataModel;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Section {

    private final String name;

    private final String iconUrl;

    public boolean isExpanded;

    public Section(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
        isExpanded = false;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
