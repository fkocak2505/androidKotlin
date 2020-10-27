package com.onedio.androidside.mvp.view.dashboard.search.customHastag;

class DefaultSelector<T> implements HashtagView.DataSelector<T> {

    public static DefaultSelector newInstance() {
        return new DefaultSelector<>();
    }

    private DefaultSelector(){}

    @Override
    public boolean preselect(T item) {
        return false;
    }
}