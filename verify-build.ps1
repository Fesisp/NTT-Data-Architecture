#!/bin/pwsh
Write-Host "Verificando ambiente de desenvolvimento..." -ForegroundColor Cyan

# Verificar Java
try {
    $javaVersion = java -version 2>&1
    if ($javaVersion -like "*24*") {
        Write-Host "✅ Java 24 encontrado" -ForegroundColor Green
    } else {
        Write-Host "❌ Java 24 não encontrado. Por favor, instale Java 24" -ForegroundColor Red
        exit 1
    }
} catch {
    Write-Host "❌ Java não encontrado" -ForegroundColor Red
    exit 1
}

# Verificar Maven
try {
    $mvnVersion = mvn -v
    Write-Host "✅ Maven encontrado" -ForegroundColor Green
} catch {
    Write-Host "❌ Maven não encontrado" -ForegroundColor Red
    exit 1
}

# Limpar builds anteriores
Write-Host "`nLimpando builds anteriores..." -ForegroundColor Cyan
mvn clean
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Falha ao limpar builds" -ForegroundColor Red
    exit 1
}

# Instalar parent POM
Write-Host "`nInstalando parent POM..." -ForegroundColor Cyan
mvn -N install
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Falha ao instalar parent POM" -ForegroundColor Red
    exit 1
}

# Build completo
Write-Host "`nExecutando build completo..." -ForegroundColor Cyan
mvn verify -Pprod
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Falha no build" -ForegroundColor Red
    exit 1
}

# Verificar se todos os JARs foram gerados
$services = @("eureka-server", "product-service", "order-service")
foreach ($service in $services) {
    $jarPath = "./$service/target/$service-1.0.0.jar"
    if (Test-Path $jarPath) {
        Write-Host "✅ JAR gerado para $service" -ForegroundColor Green
    } else {
        Write-Host "❌ JAR não encontrado para $service" -ForegroundColor Red
        exit 1
    }
}

Write-Host "`n✅ Verificação concluída com sucesso!" -ForegroundColor Green
Write-Host "`nPara executar os serviços:"
Write-Host "1. Use o docker-compose:" -ForegroundColor Yellow
Write-Host "   docker-compose up -d"
Write-Host "`n2. Ou execute manualmente:" -ForegroundColor Yellow
Write-Host "   ./start-all-services.ps1"