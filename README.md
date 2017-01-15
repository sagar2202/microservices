# microservices


A sample microservice application using Spring Cloud, Spring boot and MySQL DB. For the user provided customer id, the application fetches his customer details, cellular account details and process payment if required using 3 microservices i.e Customer, Account and Payment. Here are the details of those.

a. Customer Service: Provides details like customer name, billing address.

b. Account Service:  Provides details like current balance, current available data, due date etc.

c. Payment Service: Processes the payment , provides payment id, payment status etc.
![ScreenShot](https://github.com/sagar2202/microservices/blob/master/ServiceArchitecture.jpg)

On top we have Composite service which aggregates all the 3 services. So customer is lookup-ed up we get the customer details, account details and if the due date is today then payment is made and DB is updated. To keep the example simple, we will not be building the front end as of now and also not provided the credit card service implementation. We will test the application using POSTMAN app.
