@echo off
echo Running all tests...

echo Testing Eureka Server...
cd eureka-server
call mvn test
cd ..

echo Testing API Gateway...
cd api-gateway
call mvn test
cd ..

echo Testing Product Service...
cd product-service
call mvn test
cd ..

echo Testing Order Service...
cd order-service
call mvn test
cd ..

echo All tests completed.