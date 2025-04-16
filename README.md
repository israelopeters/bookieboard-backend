## Bookieboard app backend specification
Backend API service to power the Bookieboard Android app

# Bookieboard API
Bookieboard is a simple app to help you test your bibliophilic depths relative to other bibliophiles, just for fun! This is the backend API that allows a client (e.g., Android, iOS, desktop, or web client) access to the data store.

## MVP Features
A user can perform the following actions:

- Sign up
- Sign in
- Select difficulty level
- Take quizzes
- View bookieboard (leaderboard)

## Using the API
### From Cloud Instance
The application has been deployed on the cloud. To consume the API, copy and visit the URL below and create an account. The API should be easy to navigate from there; for example, you can add a new question (please use responsibly) and view existing questions.

```
http://bookieboardapi-env.eba-2imwjb2j.eu-west-2.elasticbeanstalk.com/admin/signup
```

### Running Locally
You can run the application quickly on your local machine with your web browser and an in-memory database. Follow these steps:


1. Fork and clone the repo.

2. Open the application.properties file and change the active profile from "dev" to "local".

```
spring.profiles.active=local
```

3. Ensure that the username and password in the application-local.properties file have been cleared.

```
spring.datasource.username=
spring.datasource.password=
```

4. Run the application with your IDE's run button or type the following command in your CLI.

```
mvn spring-boot:run
```

5. Enter the following into your browser's address bar.

```
http://localhost:9090/admin/signup
```

6. Create an account and sign in. After signing in, you can navigate the system as an admin.

7. Since you are running locally using an in-memory database, there are no persisted questions. Try adding new questions!


## Tools Used
* [Spring Boot](https://spring.io/projects/spring-boot) - Java framework
* [Spring Security](https://spring.io/projects/spring-security) - Java-based authentication and authorization framework
* [Maven](https://maven.apache.org/) - Dependency management
* [Docker](https://www.docker.com/) - Application containerisation for cloud deployment
* [AWS RDS](https://aws.amazon.com/rds/) - Database cloud hosting
* [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/) - Application cloud hosting

## Author

* **Israel Peters** - *Entire Project* - [israelopeters](https://github.com/israelopeters)



