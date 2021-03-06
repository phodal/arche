import React, {Component} from 'react'
import {View, WebView, Dimensions} from "react-native";
import Toast from 'dore-toast';
import RNDeviceInfo from "react-native-device-info";
import Dore from 'dore';

let {
  height: deviceHeight,
  width: deviceWidth
} = Dimensions.get('window');

export default class ExampleWebView extends Component {
  static componentName = 'ExampleWebView';

  constructor() {
    super();
    this.state = {
      isLoading: true
    };
    Dore.inject([
      {
        name: 'Toast',
        class: Toast
      },{
        name: 'DeviceInfo',
        class: RNDeviceInfo
      }]);
  }

  onMessage = evt => {
    let eventData = JSON.parse(evt.nativeEvent.data);
    Dore.handleMessage(evt, this.webView)
  };

  componentDidMount() {
    Dore.addHandler(this.webView);
  }

  componentWillUnmount() {
    Dore.removeHandler();
  }

  onWebViewLoadStart = () => {
    if (this.state.isLoading) {
      this.webView.injectJavaScript('window.isPhone = true;');
    }
  };

  render() {
    const source = require('./ionic/index.html');

    return (
      <View>
        <WebView
          bounces={false}
          startInLoadingState={false}
          allowUniversalAccessFromFileURLs
          ref={webView => {
            this.webView = webView
          }}
          source={source}
          style={{width: deviceWidth, height: deviceHeight}}
          onMessage={this.onMessage}
          onLoadStart={this.onWebViewLoadStart}
        />
      </View>
    )
  }
}