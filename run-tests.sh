#!/bin/bash
echo "Running all tests..."

echo "Testing Eureka Server..."
cd eureka-server
mvn test
cd ..

echo "Testing API Gateway..."
cd api-gateway
mvn test
cd ..

echo "Testing Product Service..."
cd product-service
mvn test
cd ..

echo "Testing Order Service..."
cd order-service
mvn test
cd ..

echo "All tests completed."