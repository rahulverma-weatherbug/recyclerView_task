import React, { Component } from 'react';
//import PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View
} from 'react-native';
const NativeRealRecyclerItemView = requireNativeComponent('RealRecyclerItemView', null);

class RealRecyclerItemView extends Component{

    constructor(props){
        super(props)
        this.state={
            innerRowID:this.props.rowID
        }

        console.log(this.props.rowID);
    }

    
  
//   getInitialState(){
// 	return{
// 		innerRowID:this.props.rowID
// 	};  
//   }
  onUpdateView = (event)=>{
    const {rowID} = event.nativeEvent;
    if(__DEV__)console.log("onUpdateView:new="+rowID+", old="+this.state.innerRowID);
	if(this.state.innerRowID!==rowID){
		this.props.rowID=rowID;
		this.setState({innerRowID:rowID});

        console.log("on update func innerrowid",this.state.innerRowID)
	}
  }
  render() {
    return(<NativeRealRecyclerItemView
			{...this.props}
			onUpdateView={this.onUpdateView}
			>
				{this.props.renderRow(this.state.innerRowID)}
			</NativeRealRecyclerItemView>
		);
  }
  componentWillUnmount(){
	  this.props.renderRow=undefined;
  }
}

RealRecyclerItemView.getDefaultProps= {
    rowID:-1,
    renderRow:undefined,
}

export default RealRecyclerItemView;