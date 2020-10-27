package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu.interfaced;


import com.onedio.androidside.mvp.model.dashboard.onedioFragment.dataModel.Item;
import com.onedio.androidside.mvp.model.dashboard.onedioFragment.dataModel.Section;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface ItemClickListener {
    void itemClicked(Item item);
    void itemClicked(Section section);
}
