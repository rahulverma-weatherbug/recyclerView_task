package com.rnnativemodule;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.EventDispatcher;

import java.util.ArrayList;
import java.util.List;

public class RNRecyclerView extends RecyclerView {
    private List<View> mRecycleViews = null;
    private int mRowHeight;
    private int mHoldItems;
    private EventDispatcher mEventDispatcher;

    private final RNAdapter mRNAdapter;
    public RNRecyclerView(Context context) {
        super(context);
        setLayoutManager(new LinearLayoutManager(getContext()));
        mRNAdapter =new RNAdapter();
        setAdapter(mRNAdapter);
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        final int w = MeasureSpec.getSize( MeasureSpec.makeMeasureSpec(widthSpec, MeasureSpec.AT_MOST)), h = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(heightSpec, MeasureSpec.AT_MOST));
        setMeasuredDimension(w, h);
    }
    void addNewView(View view) {
        final RNRecyclerItemView childView = (RNRecyclerItemView) view;
        if (mRecycleViews == null) {
            mRecycleViews = new ArrayList<>(mHoldItems);
        }
        if (mRecycleViews.size() < mHoldItems) {
            mRecycleViews.add(childView);
            childView.setHeight(mRowHeight);
        }
        mRNAdapter.notifyDataSetChanged();
    }


    void removeAllView() {
        if (mRecycleViews != null) {
            mRecycleViews.clear();
        }
        mEventDispatcher = null;
        mRNAdapter.setNumRows(0);
    }

    void setNumRows(int dataSize) {
        mRNAdapter.setNumRows(dataSize);
    }

    void setRowHeight(int rowHeight) {
        mRowHeight = (int) PixelUtil.toPixelFromDIP(rowHeight);
        final int height = Math.max(DisplayMetricsHolder.getScreenDisplayMetrics().heightPixels, DisplayMetricsHolder.getScreenDisplayMetrics().widthPixels);
        mHoldItems = Math.round(1.6f * height / this.mRowHeight);
        if (mHoldItems < 6) mHoldItems = 6;
    }

    private class RNAdapter extends Adapter<ViewHolder> {
        private int mDataSize = 0;
        private int mUPos = 0;

        public void setNumRows(int mDataSize) {
            this.mDataSize = mDataSize;
            notifyDataSetChanged();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final int p = mUPos >= mRecycleViews.size() ? mUPos % mRecycleViews.size() : mUPos;
            View view = mRecycleViews.get(p);
            if (view.getParent() != null) {
                for (int i = 0; i < mRecycleViews.size(); i++) {
                    View v = mRecycleViews.get(i);
                    if (v.getParent()==null) {
                        view = v;
                        mUPos = i;
                        break;
                    }
                }
            } else {
                ++mUPos;
            }
            return new ViewHolder(view) { };
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.itemView instanceof RNRecyclerItemView) {
                final RNRecyclerItemView childView = (RNRecyclerItemView) holder.itemView;
                childView.setInnerRowID(position);
                childView.setHeight(mRowHeight);
            }
        }
        @Override
        public int getItemCount() {
            return mDataSize;
        }
    }
}

