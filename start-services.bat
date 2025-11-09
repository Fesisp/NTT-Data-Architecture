@echo off
echo Starting NTT Data Microservices...

echo Starting Eureka Server...
start cmd /k "cd eureka-server && mvn spring-boot:run"

timeout /t 30

echo Starting API Gateway...
start cmd /k "cd api-gateway && mvn spring-boot:run"

timeout /t 20

echo Starting Product Service...
start cmd /k "cd product-service && mvn spring-boot:run"

timeout /t 20

echo Starting Order Service...
start cmd /k "cd order-service && mvn spring-boot:run"

echo All services are starting up. Please wait...
echo.
echo Services URLs:
echo Eureka: http://localhost:8761
echo API Gateway: http://localhost:8700
echo Product Service: http://localhost:8100
echo Order Service: http://localhost:8200