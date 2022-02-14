import React, { Component } from 'react';
import {
  requireNativeComponent,
  NativeModules,
  Dimensions,
  View
} from 'react-native';

const ReactNative = require('ReactNative');
import RecyclerItemView from './RecyclerItemView.android';
const NativeRecyclerView = requireNativeComponent('RNRecyclerView', null);

class RecyclerView extends Component {

  render() {
    const height = Dimensions.get('window').height;
    var rCount = Math.round(height / this.props.rowHeight * 1.6);
    // if (rCount < 9) rCount = 9;
    var items = [];
    let i = 0;
    for (i = 0; i < rCount; i++) {
      items.push(
        <RecyclerItemView
          rowID={i}
          // type={2}
          renderRow={this.props.renderRow}
          key={'r_' + i}
        />
      );
    }

    return (
      <NativeRecyclerView
        {...this.props}
      >
        {items}
      </NativeRecyclerView>
    );
  }

};
export default RecyclerView;
