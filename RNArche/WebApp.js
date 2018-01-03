import React from 'react';
import { StyleSheet, View } from 'react-native';
import ExampleWebView from './ExampleWebview';

export default class WebApp extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <ExampleWebView />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
