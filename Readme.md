

### Installation Steps (To Be Packaged using Docker Soon)
1. Install Maven Dependencies: `./mvnw clean install`
2. Start Cockroach DB using docker: `docker run  --name cockroach-container -p 26257:26257 -p 8080:8082 \
   -e "POSTGRES_USER=admin" -e "POSTGRES_DB=defaultdb" \
   cockroachdb/cockroach:v21.1.11 start-single-node \
   --insecure`
3. Start the application: Use Intellij


### Trying out APIs
Use the Postman Collection given in `src/main/resources/postman/`

### Details:
The Repository implements a basic CRUD application using Spring Boot. It follows the hexagonal architecture, and uses cockroachdb for backend. The database contains two tables, one column of which is related to the other using foreign key.