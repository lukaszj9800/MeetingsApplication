# MeetingsApplication
#### Meetings application created for a project during college.

## General info
Meetings full stack application created by using Spring and Angular. It allows logged in user to chceck and join to available events and to create their own events.

# Login page
<img src = "https://github.com/lukaszj9800/MeetingsApplication/blob/main/images/login_page.png" />


# Main page
## Features
* Click on "My events" to view the events of the currently logged in user
* Add a new event by the logged in user
* Click on available event to see details
* Click on "Config" to change currently logged in user data
<img src = "https://github.com/lukaszj9800/MeetingsApplication/blob/main/images/main_page.png" />

# Book details page
<img src = "https://github.com/DominikGazda/LibraryApp/blob/master/images/book-details.png" />

# Author page
## Features
* Show books assigned to  author
* Show author list
<img src = "https://github.com/DominikGazda/LibraryApp/blob/master/images/authors.png" />

# Cart page
## Features
* Show books in cart
* Increase or decrease books quantity in cart
<img src = "https://github.com/DominikGazda/LibraryApp/blob/master/images/cart-list.png" />

# Checkout page
<img src = "https://github.com/DominikGazda/LibraryApp/blob/master/images/checkout.png" />
	
## Technologies
Project is created with:
* Java 11
* Spring Boot 2.4.0
* Spring REST
* Spring/Bean Validation
* Spring Data
* Angular CLI version 11.2.0.
* TypeScript
* JPA/Hibernate
* JUnit 5.6.0
* Mockito 3.4.4
* H2 Database
	
## Setup
To run this project, install it locally using npm:

1. Download or Clone project:
```
https://github.com/DominikGazda/LibraryApp.git
```
2. Import project as maven
```
Import -> Import as Maven project
```
3. Run LibraryApplication.class
4. Go to url below (application is using embedded server)
```
http://localhost:8080/
```
5. To check database go to url below
```
http://localhost:8080/h2-console
```

## Features

* borrow books in libary
* return books to library

## Rest documentation
*  [Address entity](/restApiDocs/address.md)
*   [Author entity](/restApiDocs/author.md)
*  [Book entity](/restApiDocs/book.md)
*  [Customer entity](/restApiDocs/customer.md)
*  [Librarian entity](/restApiDocs/librarian.md)
*  [Loan entity](/restApiDocs/loan.md)
*  [Publisher entity](/restApiDocs/publisher.md)




