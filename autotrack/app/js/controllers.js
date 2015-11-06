'use strict';

/* Controllers */

var app = angular.module('autoApp', []);
var data = [
                 {"sequence": "1", "reading":"100", "fuel":"10" , "amount" : "100"},
                 {"sequence": "2", "reading":"150", "fuel":"10" , "amount" : "100"},
                 {"sequence": "3", "reading":"200", "fuel":"10" , "amount" : "100"}
             ];			
			
app.controller('autoCtrl', function($scope) {		
	  $scope.readings = data; //REST call to get data
	  $scope.counter = data.length;
      $scope.addReading = function(){
    	  $scope.counter = $scope.counter + 1;
    	  $scope.readings.push({'sequence': $scope.counter, 'reading':$scope.reading, 'fuel':$scope.fuel , 'amount' : $scope.amount}); //REST call to add data
	      $scope.reading = '';
	      $scope.fuel = '';
	      $scope.amount = '';	  
      };
});