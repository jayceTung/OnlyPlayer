package com.asuper.onlyplayer.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asuper.onlyplayer.R;
import com.asuper.onlyplayer.base.BaseFragment;
import com.asuper.onlyplayer.base.WrapContentLinearLayoutManager;
import com.asuper.onlyplayer.presenter.impl.FileListPresenter;
import com.asuper.onlyplayer.ui.adapter.FileListAdapter;
import com.asuper.onlyplayer.view.FileListView;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Super on 2016/10/14.
 */
public class FileListFragment extends BaseFragment
    implements FileListView {
    private static final String TAG = "FileListFragment";
    private static final String KEY_PARAM1 = "param1";
    private static final String KEY_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View rootView;
    private FileListPresenter mPresenter;
    private FileListAdapter mFileAdapter;

    @Bind(R.id.files_view)
    RecyclerView mFilesView;

    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshView;

    @Bind(R.id.FAM)
    FloatingActionMenu mFloatingView;


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
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    protected void initViews(View contentView) {
        mFileAdapter = new FileListAdapter(getContext());
        mRefreshView.setColorSchemeResources(R.color.colorAccent);
        mRefreshView.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mFilesView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        mFilesView.setAdapter(mFileAdapter);
    }

    @Override
    protected void initListeners() {
        mPresenter = new FileListPresenter(this);
        mPresenter.loadData(Environment.getExternalStorageDirectory());
        mRefreshView.setRefreshing(true);
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(Environment.getExternalStorageDirectory());
            }
        });
    }

    @Override
    protected void initData() {
        File rootFile = Environment.getExternalStorageDirectory();
        Log.i(TAG, "rootFile = " + rootFile);
    }

    @Override
    public void onDataSuccess(List<File> data) {
        mFileAdapter.addList(data);
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void onHideProgressBar() {
        mRefreshView.setRefreshing(false);
    }

    @Override
    public void onShowProgressBar() {
        mRefreshView.setRefreshing(true);
    }
}
