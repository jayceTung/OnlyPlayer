package com.asuper.onlyplayer.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asuper.onlyplayer.R;
import com.asuper.onlyplayer.base.BaseFragment;
import com.asuper.onlyplayer.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Super on 2016/10/14.
 */
public class FileListFragment extends BaseFragment {
    private static final String TAG = "FileListFragment";
    private static final String KEY_PARAM1 = "param1";
    private static final String KEY_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View rootView;

    private List<File> fileList = new ArrayList<>();

    public static FileListFragment newInstance(String param1, String param2) {
        FileListFragment fragment = new FileListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PARAM1, param1);
        bundle.putString(KEY_PARAM2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(KEY_PARAM1);
            mParam2 = getArguments().getString(KEY_PARAM2);
        }
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_list, container);
        return rootView;
    }

    @Override
    protected void initViews(View contentView) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        File rootFile = Environment.getExternalStorageDirectory();
    }

    private Observable<File> listFile(File file) {
        if (file.isDirectory()) {
            return Observable.from(file.listFiles()).flatMap(new Func1<File, Observable<File>>() {
                @Override
                public Observable<File> call(File file) {
                    return null;
                }
            });
        } else {
            return Observable.just(file).filter(new Func1<File, Boolean>() {

                @Override
                public Boolean call(File file) {
                    return file.exists() && file.canRead() && FileUtil.isVideo(file);
                }
            });
        }
    }
}
