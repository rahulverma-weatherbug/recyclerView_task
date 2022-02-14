package com.rnnativemodule;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class RNRecyclerItemViewManager extends ViewGroupManager<RNRecyclerItemView> {
    @Override
    public RNRecyclerItemView createViewInstance(ThemedReactContext context) {
        return new RNRecyclerItemView(context);
    }

    @Override
    public String getName() {
        return RNRecyclerItemView.class.getSimpleName();
    }

    @ReactProp(name = "innerRowID")
    public void setInnerRowID(RNRecyclerItemView view, int val) {
        view.setInnerRowID(val);
    }

    @Override
    public
    @Nullable
    Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder()
                .put("onUpdateView", MapBuilder.of("registrationName", "onUpdateView"))
                .build();
    }

    // android function --> js function
}
