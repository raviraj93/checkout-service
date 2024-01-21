# Checkout Service

### Description
- Simple application which calculates the total price of the scanned items.
- It exposes two endpoint, one to scan items and one to get total price.

### Assumptions
- It clears the scanned items list once the total value is fetched.
- Uses the docker mongo service to store the items and pricing rules.
- Storing pricing rules gives the flexibility to dynamically reconfigure the rules and modify also.

### Further Enhancements
- Currently we have only inserted 5 skus and 3 different rules in the collections.
- We can expose endpoints to insert new rules on runtime.


### Programming language, technologies and libraries
- Java 17
- Gradle
- Spring Boot
- lombok

### Prerequisites

Java 17 and gradle should be installed

### How to run the project

Go to the project directory and run the following commands:
- build the project

```shellscript
make build-and-run
```

1. To scan the elements, use the endpoint. POST 
```
localhost:8080/v1/api/cart/scan?sku=B
```
2. To get the total of the cart. GET endpoint
```
localhost:8080/v1/api/cart/total'
