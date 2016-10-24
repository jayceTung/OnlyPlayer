package com.asuper.onlyplayer.view;

import java.io.File;
import java.util.List;

/**
 * Created by Super on 2016/10/14.
 */
public interface FileListView extends MvpView {

    /**
     * 加载成功数据
     * @param data
     */
    void onDataSuccess(List<File> data);

    /**
     * 加载失败
     * @param e
     */
    void onFailure(Throwable e);

    /**
     * 隐藏加载
     */
    void onHideProgressBar();

    /**
     * 显示加载
     */
    void onShowProgressBar();
}
