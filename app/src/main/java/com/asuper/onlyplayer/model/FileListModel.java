package com.asuper.onlyplayer.model;

import android.util.Log;

import com.asuper.onlyplayer.presenter.impl.FileListPresenter;
import com.asuper.onlyplayer.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Super on 2016/10/14.
 */
public class FileListModel implements BaseModel {
    private static final String TAG = "FileListModel";

    private FileListPresenter presenter;
    private CompositeSubscription subscription;
    private List<File> mList;

    public FileListModel(FileListPresenter presenter, CompositeSubscription subscription) {
        this.presenter = presenter;
        this.subscription = subscription;
        mList = new ArrayList<File>();
    }

    @Override
    public void loadData(Object... args) {
        File file = (File) args[0];
        Observable.just(file)
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return listFiles(file);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<File>() {
                    @Override
                    public void onCompleted() {
                        if (mList != null && mList.size() > 0) {
                            presenter.loadSuccess(mList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.loadFailure(e);
                    }

                    @Override
                    public void onNext(File file) {
                        Log.i(TAG, "file name = " + file.getName());
                        mList.add(file);
                    }
                });
    }

    @Override
    public void destroy() {
        subscription.unsubscribe();
        subscription = null;
    }

    /**
     * rxjava递归查询内存中的视频文件
     * 在此方法中应该可以做groupby操作，这样能简化操作，日后再研究
     * @param f
     * @return
     */
    private Observable<File> listFiles(final File f){
        if(f.isDirectory()){
            return Observable.from(f.listFiles())
                    .flatMap(new Func1<File, Observable<File>>() {
                        @Override
                        public Observable<File> call(File file) {
                            /**如果是文件夹就递归**/
                            return listFiles(file);
                        }
                    });
        } else {
            /**filter操作符过滤视频文件,是视频文件就通知观察者**/
            return Observable.just(f)
                    .filter(new Func1<File, Boolean>() {
                        @Override
                        public Boolean call(File file) {
                            return f.exists() && f.canRead() && FileUtil.isVideo(f);
                        }
                    });
        }
    }
}
