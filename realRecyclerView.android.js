import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
  requireNativeComponent,
  NativeModules,
  Dimensions,
  View
} from 'react-native';

const ReactNative = require('ReactNative');
import RealRecyclerItemView from './realRecyclerItemView.android';
const NativeRealRecyclerView = requireNativeComponent('RealRecyclerView', null);

class RealRecyclerView extends Component{
//   propTypes: {
// 	...View.propTypes,
// 	numRows: React.PropTypes.number.isRequired,
// 	rowHeight: React.PropTypes.number.isRequired,
// 	renderHeader: React.PropTypes.func,
//   },
componentWillUnmount(){
    this.props.renderRow=undefined;
}
  render() {
	const height=Dimensions.get('window').height;
	var rCount =Math.round(height/this.props.rowHeight*1.6);
	if(rCount<9)rCount=9;
    var items = [];
    let i=0;
    for (i=0; i<rCount; i++) {
      items.push(
		<RealRecyclerItemView
		  rowID={i}
		  type={2}
		  renderRow={this.props.renderRow}
		  key={'r_' + i}
		 />
      );
    }

    return (
        <NativeRealRecyclerView
		  {...this.props}
        >
          {items}
        </NativeRealRecyclerView>
    );
  }
  
};

export default RealRecyclerView;
