## Train Ticker Management

This project implements a RESTful API for managing Train ticket Management System for london to france. The API allows for purchase train ticket, modification of user data and retrieval of  information of user. Additionally, Docker support is provided for easy deployment.
## Technology stack

* Java

* Spring Boot

* spring security

* Logging

* MySQL

* Docker
## Features


1. User should include first and last name, email address

2. The user is allocated a seat in the train. Assume the train has only 2 sections, section A and section B.

3. An API that shows the details of the receipt for the user

4. An API that lets you view the users and seat they are allocated by the requested section

5. An API to remove a user from the train

6. An API to modify a user's seat
## Getting Started

Follow these steps to set up and run the project:

1. Clone the repository:

 git clone 
 
        https://github.com/Rohini205/TrainTicketManagement

2. Navigate to the project directory:

        cd trainticketmgt

3. Build the Docker image:

       docker build -t trainticketmgt 

4. Run the Docker container:

        docker run -p 8080:8080 rohinitibile/trainticketmgt

## MySQL Database Integration:

* Use of Spring Data JPA and Hibernate to map entities to MySQL database tables.

* Repository class for performing CRUD operations on the database.
## API Endpoints

* POST /api/tain/purchese: To purchese the ticket.

* GET /api/train/seats: Retrieve information about an ticket details.

* PUT /api/train/user/{id}/modify: To modify the detais.

* DELETE /api/train/user/{id}: Deleting the all the details.
## Testing

* Unit tests: JUnit for critical components.

* Integration tests: Test API endpoints.
## Security

* Input validation and handling edge cases.

* HTTPS for secure communication.
## Docker Deployment

Follow the Docker commands mentioned above to build and run the Docker image for easy deployment.
## Conclusion

This project provides a robust RESTful API for managing Train ticket Management System, with Docker support for seamless deployment.
