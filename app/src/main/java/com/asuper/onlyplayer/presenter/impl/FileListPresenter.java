package com.asuper.onlyplayer.presenter.impl;

import com.asuper.onlyplayer.model.FileListModel;
import com.asuper.onlyplayer.presenter.BasePresenter;
import com.asuper.onlyplayer.presenter.IFileListPresenter;
import com.asuper.onlyplayer.view.FileListView;

import java.io.File;
import java.util.List;

/**
 * Created by Super on 2016/10/24.
 */

public class FileListPresenter extends BasePresenter<FileListView>
        implements IFileListPresenter {
    private FileListModel mFileListModel;
    private FileListView mFileListView;

    public FileListPresenter(FileListView fileListView) {
        this.mFileListView = fileListView;
        this.mFileListModel = new FileListModel(this, mCompositeSubscription);
    }

    @Override
    public void loadData(File file) {
        mFileListModel.loadData(file);
    }

    @Override
    public void loadSuccess(List<File> list) {
        mFileListView.onHideProgressBar();
        mFileListView.onDataSuccess(list);
    }

    @Override
    public void loadFailure(Throwable e) {
        mFileListView.onHideProgressBar();
        mFileListView.onFailure(e);
    }

    @Override
    public void detachView() {
        super.detachView();
        mFileListModel.destroy();
    }
}
