package com.onedio.androidside.mvp.view.dashboard.onedioFragment.popupMenu.interfaced;

/**
 * Created by bpncool on 2/24/2016.
 */

import com.onedio.androidside.mvp.model.dashboard.onedioFragment.dataModel.Section;

/**
 * interface to listen changes in state of sections
 */
public interface SectionStateChangeListener {
    void onSectionStateChanged(Section section, boolean isOpen);
}