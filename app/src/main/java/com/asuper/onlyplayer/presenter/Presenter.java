package com.asuper.onlyplayer.presenter;

import com.asuper.onlyplayer.view.MvpView;

/**
 * Created by Super on 2016/10/14.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V view);

    void detachView();
}
