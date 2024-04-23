**Setting up**

1. **Build the Project:**
    - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
    - Let the IDE resolve dependencies and build the project.
    - Ensure that Java 21 is set as the SDK for this project.

2. **Run the Application:**
    - Run the main application class, DigitalLibraryApplication.
    - Wait for the application to start, and check the console for startup messages.

3. **Access Swagger UI:**
    - Open a web browser and navigate to the Swagger UI URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
    - Explore and interact with the API endpoints documented using Swagger.

4. **Monitor Cache:**
    - To monitor the cache, access the cache monitoring URL: [http://localhost:8080/actuator/caches](http://localhost:8080/actuator/caches)

5. **Monitor Prometheus Metrics:**
    - Access the Prometheus metrics endpoint: [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)
    - For better visualization, utilize Grafana to understand the metrics.

**Design choices and technologies employed**

In our project, we adopted several technologies and design choices to ensure efficient development, scalability, and maintainability.

- **Spring Boot 3:** Easy to use, extensive ecosystem, and robust features for building enterprise-grade applications.
- **Spring Data JPA:** Simplifies CRUD operations and allows repository-based database interactions.
- **Lombok:** Reduces boilerplate code in Java classes, enhancing readability and maintainability.
- **Swagger/OpenAPI:** Provides API documentation and enables interactive API exploration.
- **Caching:** Improves performance and scalability by caching frequently accessed data.
- **Prometheus:** Monitors the health and performance of the application by collecting and aggregating metrics.
- **RESTful API Design:** Follows RESTful principles for simplicity, scalability, and interoperability.
- **Domain-Driven Design (DDD) and Clean Architecture:** Ensures accurate representation of the business domain and separation of concerns.

**Database Design Choices**

![Diagram](./src/main/resources/db/diagram.png)

- **Normalized Data Structure:** Minimizes redundancy and ensures data integrity.
- **Foreign Key Relationships:** Establish associations between entities for efficient data retrieval.
- **Scalability and Performance:** Organizes data into separate tables based on entities to facilitate scalability.
- **Normalized Reviews Table:** Simplifies querying and analyzing review data independently.
- **Timestamps for Auditing:** Tracks when reviews were submitted for auditing and trend analysis.
