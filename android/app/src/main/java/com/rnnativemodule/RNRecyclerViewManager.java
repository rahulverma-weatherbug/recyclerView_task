package com.rnnativemodule;

import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNRecyclerViewManager extends ViewGroupManager<RNRecyclerView> {
    @Override
    public String getName() {
        return RNRecyclerView.class.getSimpleName();
    }

    @Override
    protected RNRecyclerView createViewInstance(ThemedReactContext reactContext) {
        RNRecyclerView view = new RNRecyclerView(reactContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public void addView(RNRecyclerView parent, View child, int index) {
        parent.addNewView(child);
    }
    @ReactProp(name = "numRows")
    public void setNumRows(RNRecyclerView parent, int size) {
        parent.setNumRows(size);
    }

    @ReactProp(name="rowHeight")
    public void setRowHeight(RNRecyclerView parent, int val){
        parent.setRowHeight(val);
    }

    @Override
    public int getChildCount(RNRecyclerView parent) {
        return parent.getChildCount();
    }

    @Override
    public void removeAllViews(RNRecyclerView parent) {
        parent.removeAllView();
    }
}
