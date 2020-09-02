'use strict';

/* jasmine specs for controllers go here */
describe('AutoApp controllers', function() {

  describe('autoCtrl', function(){

    beforeEach(module('autoApp'));

    it('should create "readings" model with 3 reading', inject(function($controller) {
      var scope = {},
          ctrl = $controller('autoCtrl', {$scope:scope});

      expect(scope.readings.length).toBe(3);
    }));

  });
});