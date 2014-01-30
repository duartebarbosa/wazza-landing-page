angular.module('LandingPage', [])

.controller('LandingPageFormController', ['$scope', function ($scope) {
  console.log("hello");

  $scope.newSignup = function(){
    console.log("new sign up");
  };

}]);
