import React, { Component } from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';
const NativeRecyclerItemView = requireNativeComponent('RNRecyclerItemView', null);

class RecyclerItemView extends Component {

  constructor(props) {
    super(props)
    this.state = {
      innerRowID: this.props.rowID
    }

  }

  onUpdateView = (event) => {
    const { rowID } = event.nativeEvent;
    if (this.state.innerRowID !== rowID) {
      this.props.rowID = rowID;
      this.setState({ innerRowID: rowID });
    }
  }
  render() {
    return (<NativeRecyclerItemView
      {...this.props}
      onUpdateView={this.onUpdateView}
    >
      {this.props.renderRow(this.state.innerRowID)}
    </NativeRecyclerItemView>
    );
  }
  componentWillUnmount() {
    this.props.renderRow = undefined;
  }
}
export default RecyclerItemView;  