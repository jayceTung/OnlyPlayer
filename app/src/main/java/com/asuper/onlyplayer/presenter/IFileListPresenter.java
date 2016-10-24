package com.asuper.onlyplayer.presenter;

import java.io.File;
import java.util.List;

/**
 * 文件presenter接口
 * Created by Super on 2016/10/24.
 */

public interface IFileListPresenter {

    void loadData(File file);

    void loadSuccess(List<File> list);

    void loadFailure(Throwable e);
}
