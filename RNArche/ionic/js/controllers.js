angular
  .module('starter.controllers', [])
  .controller('DashCtrl', function ($scope, $ionicPlatform) {
    $scope.version = null;
    $scope.text = 'Text Copy';
    $scope.copyText = '';
    DoreClient.addStateListener();
    $ionicPlatform.on('STATE', function (event) {
      $scope.state = event.detail.data;
      $scope.$apply();
    });

    $ionicPlatform.on('ANDROID_BACK', function (event) {
      DoreClient.showToast('ANDROID_BACK');
    });

    DoreClient.getAppVersion().then(function (data) {
      $scope.version = data.version;
      $scope.$apply();
    });
    $scope.showToast = function () {
      DoreClient.showToast('this is a toast');
    };
    $scope.showToastCenter = function () {
      DoreClient.showToast('this is a toast', 'long', 'center');
    };
  })

  .controller('DeviceCtrl', function ($scope) {
    $scope.getAppVersion = function () {
      DoreClient.getAppVersion().then(function (data) {
        $scope.version = data.version;
        $scope.$apply();
      });
    };
    $scope.getUniqueID = function () {
      DoreClient.getUniqueID().then(function (data) {
        $scope.uniqueID = data.uniqueID;
        $scope.$apply();
      });
    };
    $scope.getBrand = function () {
      DoreClient.getBrand().then(function (data) {
        $scope.brand = data.brand;
        $scope.$apply();
      });
    };
    $scope.getModel = function () {
      DoreClient.getModel().then(function (data) {
        $scope.model = data.model;
        $scope.$apply();
      });
    };
    $scope.getSystemName = function () {
      DoreClient.getSystemName().then(function (data) {
        $scope.systemName = data.systemName;
        $scope.$apply();
      });
    };
    $scope.isEmulator = function () {
      DoreClient.isEmulator().then(function (data) {
        $scope.isEmulator = data;
        $scope.$apply();
      });
    };
    $scope.isTablet = function () {
      DoreClient.isTablet().then(function (data) {
        $scope.isTablet = data;
        $scope.$apply();
      });
    };
  });
