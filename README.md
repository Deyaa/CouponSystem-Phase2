# CouponSystem-Phase2
Java, Spring Boot, Spring Web

**Bref**:
The coupon system contains three client types: 
Administrator - He can manages the companies and customers.
Company - Can manage his coupons.
Customer - Can purchase coupon.

**Infra**:
* LoginManager - used as the first entry to the system - for login.
* Controllers - Web api's to manipulate the capalities system for all client types.
* ControllerAdvice (advice) - used to catch exception threw from the controllers.
* CommandLineRunner:
   * BootstrapLogin - This clr starts on the init of the system, the component will test all client services (Admin, Company, Customer)
                    in addition to test the logic in various cases, it is also fill the db with appropriate data.
   * All the other clr - do testing for system features.
* Job (Daily Removal) - Runs every few second, and remove all expired coupons from the database.
* Repos - Responsible for working with the database.
* Services - Responsible for logic.

**How to Work with the system?**

1. Starting the system will load the BootstrapLogin clr, the BootstrapLogin will fill the db with data, and will display all the system logic with various cases
2. The DailyRemoval will start to clear all expired coupons from db.
3. You can see all the Api's by browse to the address http://localhost:8080/swagger-ui.html
4. In order to test Web Api's, I prepared a collection test, you can use it from Postman, by importing the following address to postman    you can see all test of all api's. The   collection address is https://www.getpostman.com/collections/574addadca2ebe3f939a
 for test the api's you should run the Login api and then the rest api's, because they use the login object.




