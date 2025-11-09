#!/bin/bash
echo "Starting NTT Data Microservices..."

echo "Starting Eureka Server..."
cd eureka-server && mvn spring-boot:run &

sleep 30

echo "Starting API Gateway..."
cd ../api-gateway && mvn spring-boot:run &

sleep 20

echo "Starting Product Service..."
cd ../product-service && mvn spring-boot:run &

sleep 20

echo "Starting Order Service..."
cd ../order-service && mvn spring-boot:run &

echo "All services are starting up. Please wait..."
echo
echo "Services URLs:"
echo "Eureka: http://localhost:8761"
echo "API Gateway: http://localhost:8700"
echo "Product Service: http://localhost:8100"
echo "Order Service: http://localhost:8200"