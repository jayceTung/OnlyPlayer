package com.asuper.onlyplayer.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Base RecyclerViewHolder
 *
 */
public abstract class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract View getView();

}
