package com.asuper.onlyplayer.presenter;

import com.asuper.onlyplayer.utils.Log;
import com.asuper.onlyplayer.view.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Super on 2016/10/14.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {
    private static final String TAG = "BasePresenter";

    private T mvpView;
    public CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(T view) {
        Log.d(TAG, "attachView" + view.toString());
        this.mvpView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        Log.d(TAG, "detachView");
        this.mvpView = null;
        mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }

    /**
     * 添加subscriber订阅者到mCompositeSubscription，
     * 方便在detachView的时候取消订阅，防止内存泄露
     * @param subscriber
     */
    public void addSubscriberToCompositeSubscription(Subscription subscriber){
        if(subscriber != null){
            this.mCompositeSubscription.add(subscriber);
        }
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public T getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
