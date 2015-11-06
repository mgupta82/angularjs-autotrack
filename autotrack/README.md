# Requirement
It's a good idea to get an actual mileage reading for your car. This provides a baseline for your understanding and improving the gas mileage of your car.

##Calculating Your Car's Mileage
1. Fill up your car's gas tank all the way. 
2. Note the odometer reading (initial reading)
3. Drive your car as you normally would, and let your gas tank deplete.
4. Get to the gas station and fill your tank again.
5. Record the amount of gas it took to refill the tank (total gas).
6. Note the odometer reading (final reading)
7. Calculate the mileage using the formula [(final reading) - (initial reading)] / [total gas]

#Solution Overview
##User Interface
1. Display all the fills till now entered by user
2. For every fill, ask the user to input current odometer reading and the litre of gas filled. User can also enter the amount paid.
3. As soon as user enter the re-fill detail, on the fly calculate the mileage by using the previous refills.

##Assumption
1. User should always fill full tank.
2. Mileage can't be calculated for the first fill.
3. Your actual gas efficiency,  may vary from stated estimates. Vehicle condition and style of driving, among other factors, can influence mileage.

#Technical Setup
We will use Angular, NodeJs , Bower , Karma for unit testing and protractor for e2e test. jasmine will be preferred lanuguate for writing BDD test cases.

##Pre-requisite
1. Install NodeJS and NPM
2. Install Bower CLI globally : npm install -g bower
3. Install Karma CLI globally : npm install -g karma-cli
4. Install Jasmine CLI globally : npm install -g jasmine
5. Install Grunt CLI globally : npm install -g karma-cli
6. Install Protractor globally : npm install -g protractor
7. Update selenium driver : webdriver-manager update
8. npm install -g grunt-cli

##NPM Package (NodeJs project)
1. npm init : This creates package.json
2. npm install http-server --save-dev : This will create Node Web Server
3. In package.json, under scripts add **"start": "http-server -a 0.0.0.0 -p 8000"**
4. If you are working from company with proxy then run the below commands

npm config set proxy "http://username:password@proxy-host:proxy-port"

npm config set https-proxy "http://username:password@proxy-host:proxy-port"

##Bower (Browser Package Manager)
1. bower init : This creates bower.json
2. bower install bootstrap --save
3. bower install angular --save
4. bower install angular-mocks --save
5. npm install bower --save-dev
6. In package.json, under scripts add **"postinstall": "bower install"**
7. Create .bowerrc with following tags

{

  "directory": "app/components",
  
  "interactive": false,
  
  "proxy":"http:proxy-host:proxy-port", "https-proxy":"http://proxy-host:proxy-port" 
  
}

##Karma and Jasmine for BDD
1. npm install karma --save-dev
2. karma init (src : app/js/**/*.js , test : test/unit/**/*.js) : This will create karma.conf.js

##Create App
1. Inside app folder, create index.html
2. Inside app/js, folder create controllers.js

##Create Jasmine Unit test
1. Inside test/unit, run jasmine init
2. Write the jasmine unit test cases and name the file as displaySpecs.js
3. In package.json, under scripts add **"test": "node node_modules/karma/bin/karma start karma.conf.js"**

##Protractor for e2e test
1. Create protractor-conf.js specifying app URL
2. npm install protractor --save-dev
3. In package.json, under scripts add **"protractor": "protractor protractor-conf.js"**

##Create Jasmine e2e test
1. Inside test/e2e, create fill-specs.js
2. describe  the test case
3. You can run the test case by running **npm run protractor**

##Create Grunt Build
1. Add Grunt to pakcage.json : npm install grunt --save-dev
2. Add Grunt uglify plugin : npm install grunt-contrib-uglify --save-dev
3. Create Gruntfile.js

##gitIgnore
Last but not the least, create .gitIgnore file to tell git that it should not track/commit node_modules, app/components and  build folders.

