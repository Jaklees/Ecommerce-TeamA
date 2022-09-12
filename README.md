# Project 2 - E-Commerce SPA

## Executive Summary
RevTech is an e-commerce single page application focused on allowing consumers to browse, search, and buy electronic products. Consumers can register accounts, view and edit their profiles, view and edit their addresses and payment methods, and view their previous orders. Products can be filtered by whether they're currently on a sale, being featured, by product category, or by price. Consumers can select the specific electronic product they enjoy, add it to a cart, then checkout. If they change their mind about products in their cart, they can remove them before they checkout. Revtech is also rich with features such as a toggle to switch between a dark and light theme, change password, notifications and more.   


# Tech Stack
 - Languages
   - Java
   - JavaScript
   - TypeScript
 - Data Persistence
   - PostgreSQL
   - Hibernate
 - AWS
   - RDS
   - S3
   - CodeBuild & CodePipeline
   - ElasticBeanstalk
 - Spring Framework
   - Spring Boot
   - Spring MVC
 - Angular

# Functional Requirements
 - Domain objects persisted in relational database via ORM
 - All CRUD functionality accessible via RESTful API
 - Single page web UI to consume RESTful API
 - Workflows to complete all user stories
 - Validate all user input
 - Unit test coverage for service-layer classes

### Stretch Goals:
 - Application is merged, tested, and deployed with a fully functional CI/CD Pipeline.

The persistence layer uses Hibernate ORM to translate between the database and the application server. The API layer abstracts away the low-level servlets with Spring Web MVC. The end users uses Angular to produce an SPA which is styled to be functional and readable. The server is in a proper layered architecture, and have adequate unit testing of the service layer. The client and server communicates in a RESTful manner, and the server is stateless. 


## User Stories
#### Guest
 - As a guest, I can register for an account.
 - As a guest, I can log in to my account.

#### User:
 - As a user, I have a profile which I can view.
 - As a user, I can browse products and add them to my cart.
 - As a user, I can remove items from my cart.
 - As a user, I can checkout to purchase the items in my cart.
 - As a User, I am able to select an amount of an item to add to my cart as I am adding an item
 - As a User, I am able to search the product list to better find the item(s) I am interested in
 - As a User, I am able to see and purchase items that are on sale for a lower price.
 - As a User, I am able able to see a list of featured products on the main page of the application
 - As a User, my session is maintained until I log out.
 - As a User, I am able to reset my password.
 - As a User, I am able to change the color scheme from the normal mode to a dark mode option.
 - As a User, I receive notifications when a transaction has occurred or a transfer has been completed

#### Admin:
 - As a admin, i can login into my admin account.
 - As a admin, i can view all products in the system.
 - As a admin, i can view add, edit and update a product in the system.
