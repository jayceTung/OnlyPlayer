package com.asuper.onlyplayer.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuper.onlyplayer.R;
import com.asuper.onlyplayer.base.Constant;
import com.asuper.onlyplayer.ui.activity.VideoActivity;
import com.asuper.onlyplayer.utils.FileUtil;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Super on 2016/10/24.
 */

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileListViewHolder>{
    private static final String TAG = "FileListAdapter";

    private List<File> mList;
    private Context mContext;

    public FileListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addList(List<File> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public FileListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_videos_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(FileListViewHolder holder, int position) {
        if (holder instanceof FileListViewHolder) {
            final File file = mList.get(position);
            holder.mNameView.setText(file.getName());
            holder.mSizeView.setText(FileUtil.getFileSize(file.length()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VideoActivity.class);
                    intent.putExtra(Constant.FILE_VIDEO_PATH, file.getAbsolutePath());
                    intent.putExtra(Constant.FILE_VIDEO_TITLE, file.getName());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        } else {
            return 0;
        }
    }

    public class FileListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivPic)
        ImageView mImageView;

        @Bind(R.id.tvName)
        TextView mNameView;

        @Bind(R.id.tvSize)
        TextView mSizeView;

        @Bind(R.id.tvlength)
        TextView mLengthView;

        public FileListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
