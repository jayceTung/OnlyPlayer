package com.asuper.onlyplayer.model;

/**
 * Created by Super on 2016/9/20.
 */
public interface BaseModel {

    /**
     * 数据层数据加载方法
     */
    void loadData(Object... args);

    void destroy();
}
