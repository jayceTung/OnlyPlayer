package com.asuper.onlyplayer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

import com.asuper.onlyplayer.R;
import com.asuper.onlyplayer.base.BaseActivity;
import com.asuper.onlyplayer.base.Constant;
import com.asuper.onlyplayer.media.IjkVideoView;
import com.asuper.onlyplayer.media.MeasureHelper;
import com.asuper.onlyplayer.utils.Log;

import butterknife.Bind;

/**
 * Created by Super on 2016/10/25.
 */

public class VideoActivity extends BaseActivity {
    private static final String TAG = "VideoActivity";

    @Bind(R.id.video_view)
    IjkVideoView mVideoView;

    @Bind(R.id.toast_text_view)
    TextView mTextView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.hud_view)
    TableLayout mHubView;

    private String mVideoPath;
    private String mVideoTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mVideoPath = getIntent().getStringExtra(Constant.FILE_VIDEO_PATH);
        mVideoTitle = getIntent().getStringExtra(Constant.FILE_VIDEO_TITLE);

        setSupportActionBar(mToolbar);
        mVideoView.setHudView(mHubView);
    }

    @Override
    protected void initData() {
        mToolbar.setTitle(mVideoTitle);
        if (!TextUtils.isEmpty(mVideoPath)) {
            mVideoView.setVideoPath(mVideoPath);
        }
        mVideoView.start();
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_toggle_ratio) {
            int aspectRatio = mVideoView.toggleAspectRatio();
            String aspectRatioText = MeasureHelper.getAspectRatioText(this, aspectRatio);
            Snackbar.make(mVideoView, aspectRatioText, Snackbar.LENGTH_SHORT);
//            customMediaController.showOnce(mToastTextView);
            return true;
        }
        /**隐藏重播选项**/
//        else if (id == R.id.action_toggle_player) {
//            int player = mVideoView.togglePlayer();
//            String playerText = IjkVideoView.getPlayerText(this, player);
//            mToastTextView.setText(playerText);
//            customMediaController.showOnce(mToastTextView);
//            return true;
//        }
        /**隐藏render选项**/
//        else if (id == R.id.action_toggle_render) {
//            int render = mVideoView.toggleRender();
//            String renderText = IjkVideoView.getRenderText(this, render);
//            mToastTextView.setText(renderText);
//            customMediaController.showOnce(mToastTextView);
//            return true;
//        }
        else if (id == R.id.action_show_info) {
            mVideoView.showMediaInfo();
        }
        /**隐藏tracks选项**/
//        else if (id == R.id.action_show_tracks) {
//            if (mDrawerLayout.isDrawerOpen(mRightDrawer)) {
//                Fragment f = getSupportFragmentManager().findFragmentById(R.id.right_drawer);
//                if (f != null) {
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.remove(f);
//                    transaction.commit();
//                }
//                mDrawerLayout.closeDrawer(mRightDrawer);
//            } else {
//                Fragment f = TracksFragment.newInstance();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.right_drawer, f);
//                transaction.commit();
//                mDrawerLayout.openDrawer(mRightDrawer);
//            }
//        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mVideoPath = getIntent().getStringExtra(Constant.FILE_VIDEO_PATH);
        mVideoTitle = getIntent().getStringExtra(Constant.FILE_VIDEO_TITLE);
        super.onNewIntent(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
