'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('my app', function() {

  beforeEach(function() {
    browser.get('app/index.html');
  });

  it("should add the fill",function(){
  	element(by.model('reading')).sendKeys('250');
  	element(by.model('fuel')).sendKeys('10');
  	element(by.model('amount')).sendKeys('100');
  	element(by.css('[value="Add"]')).click();
  	var readingList = element.all(by.repeater('reading in readings'));
  	expect(readingList.count()).toEqual(4);
  });

});
