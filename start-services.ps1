# Limpar arquivos antigos e temporários
Remove-Item -Path ".\order-service\src\main\java\com\nttdata\order" -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -Path ".\order-service\src\test\java\com\nttdata\order" -Recurse -Force -ErrorAction SilentlyContinue
Get-ChildItem -Path "." -Recurse -Filter "*.temp" | Remove-Item -Force

# Limpar e construir os projetos
cd eureka-server
mvn clean package
cd ../product-service
mvn clean package
cd ../order-service
mvn clean package
cd ..

# Iniciar os serviços em terminais separados
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd eureka-server; mvn spring-boot:run"
Start-Sleep -Seconds 30 # Aguardar o Eureka iniciar

Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd product-service; mvn spring-boot:run"
Start-Sleep -Seconds 15 # Aguardar o Product Service iniciar

Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd order-service; mvn spring-boot:run"