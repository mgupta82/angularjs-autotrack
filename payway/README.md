# Requirement
Whenever user clicks this new payment gateyway option on the Payment Channel website, it should open Payment Gateway in the new window. 

1. This website call should be secure and should  not be able to call via unauthorised Payment Channels / person
2. The URL should not be replayable
3. After successful completion the response should be returned to vendor.
4. System should be designed such that new vendor can be added on the fly.
5. Password should be enrypted and stored securely in the database.

#Solution Overview
##Interface
1. Payment Channel should first get TOKEN from the Payment Gateway
2. All the attributes will hashed and salted with PIN and TOKEN.

##Security
1. Passwords will be hashed and stored in the database. Hashed passwords cannot be reverted.

#Technical Setup
##Spring Boot
1. Open http://start.spring.io/
2. Select Embeded tomcat

##Test Client
1. Create TestGet and TestPut
