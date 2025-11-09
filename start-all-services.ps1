# Stop any running Java processes
Write-Host "Stopping any running Java processes..."
Stop-Process -Name "java" -ErrorAction SilentlyContinue
Start-Sleep -Seconds 2

# Define service paths and set working directory
$baseDir = "c:\Users\mrfel\OneDrive\Laboratorio\VSCode\NTT Data"
Set-Location $baseDir
$eurekaPath = ".\eureka-server"
$productPath = ".\product-service"
$orderPath = ".\order-service"

# Function to start a service
function Start-MicroService {
    param(
        [string]$path,
        [string]$name,
        [int]$waitTime = 20
    )
    Write-Host "Starting $name..."
    Set-Location $path
    Start-Process powershell -ArgumentList "mvn spring-boot:run" -WindowStyle Normal
    Set-Location $baseDir
    Write-Host "Waiting $waitTime seconds for $name to initialize..."
    Start-Sleep -Seconds $waitTime
}

# Start services in order
Write-Host "Starting all microservices..."

# 1. Start Eureka Server first
Start-MicroService -path $eurekaPath -name "Eureka Server" -waitTime 30

# 2. Start Product Service
Start-MicroService -path $productPath -name "Product Service" -waitTime 20

# 3. Start Order Service
Start-MicroService -path $orderPath -name "Order Service" -waitTime 20

Write-Host "All services started. Service endpoints:"
Write-Host "Eureka Server: http://localhost:8761"
Write-Host "Product Service: http://localhost:8100/swagger-ui.html"
Write-Host "Order Service: http://localhost:8200/swagger-ui.html"