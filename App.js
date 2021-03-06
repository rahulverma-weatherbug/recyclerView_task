/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Dimensions
} from 'react-native';

import RecyclerView from './RecyclerView.android.js';
const ROWS_IN_DATA_SOURCE = 20;
const dataSource = [];
for (let i = 0; i < ROWS_IN_DATA_SOURCE; i++) dataSource.push(`hi android ${i}。 row=${i}。`);

class RecyclerViewApp extends Component {
  render() {
    return (
      <RecyclerView
        style={styles.container}
        renderRow={this.renderRow}
        numRows={dataSource.length}
        rowHeight={100}
      />
    );
  }

  renderRow(rowID) {
    return (
      <View style={{
        flex: 1,
        width: Dimensions.get('window').width,
        height: 100,
        backgroundColor: '#F2F2'
      }}>
        <Text style={{
          height: 99,
          flexDirection: 'row',
          color: '#F2F',
          justifyContent: 'center',
          alignItems: 'center',
          textAlign: 'center',
        }}>{dataSource[rowID]}</Text>

        <View style={{ height: 1, backgroundColor: '#0015FF', flexDirection: 'row' }} ></View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height,
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f7f7f7',
  },
});
AppRegistry.registerComponent('RecyclerView', () => RecyclerViewApp);
