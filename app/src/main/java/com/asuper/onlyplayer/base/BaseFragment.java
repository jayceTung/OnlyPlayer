package com.asuper.onlyplayer.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    private ExitAppReceiver mReceiver = new ExitAppReceiver();

    protected Activity mActivity;
    protected View contentView;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        mActivity = getActivity();
        contentView = getContentView(inflater,container);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, contentView);
        initViews(view);
        initDatas();
        initListeners();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    /**
     * set content view
     * @param inflater
     * @return
     */
    protected abstract View getContentView(LayoutInflater inflater ,ViewGroup container);

    /**
     * init view
     * @param contentView
     * @return
     */
    protected abstract void initViews(View contentView);

    /***
     * set listeners
     */
    protected abstract void initListeners();

    /**
     * init data
     */
    protected abstract void initDatas();
    /**
     * show toast
     */
    protected  void showToast(String msg){
        if(msg != null){
            Toast.makeText(mActivity , msg,Toast.LENGTH_SHORT).show();
        }
    }
}
