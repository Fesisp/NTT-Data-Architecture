# 🛍️ Microservices Demo - Sistema de Gestão de Pedidos

![Spring](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen)
![Java](https://img.shields.io/badge/Java-24-orange)
![License](https://img.shields.io/badge/license-MIT-blue)
![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen)

> Uma demonstração prática e profissional de uma arquitetura de microserviços usando Spring Boot. Este projeto implementa um sistema completo de gestão de produtos e pedidos, perfeito para aprendizado e referência em desenvolvimento de microserviços.

## 🌟 Recursos Principais

### Características Técnicas
- **Service Discovery**: Registro e descoberta automática de serviços com Eureka
- **API Documentation**: Interface Swagger UI para teste e documentação
- **Database**: Banco de dados H2 em memória para rápida prototipagem
- **Validation**: Validação robusta com Jakarta Validation
- **Error Handling**: Sistema centralizado de tratamento de erros
- **Load Balancing**: Balanceamento de carga integrado
- **Resilience**: Circuit breakers para maior resiliência
- **Service Communication**: Comunicação eficiente com OpenFeign

### Funcionalidades de Negócio
- Cadastro e gestão de produtos
- Criação e acompanhamento de pedidos
- Integração automática entre serviços
- Consultas em tempo real
- Validações de negócio
- Monitoramento de status

## 🎯 Demonstração

### Links Rápidos
- **Dashboard de Serviços:** [http://localhost:8761](http://localhost:8761)
- **Gerenciar Produtos:** [http://localhost:8100/swagger-ui.html](http://localhost:8100/swagger-ui.html)
- **Gerenciar Pedidos:** [http://localhost:8200/swagger-ui.html](http://localhost:8200/swagger-ui.html)

### Exemplos de Uso com PowerShell

1. **Criar um Produto**
```powershell
$headers = @{'Content-Type'='application/json'}
$body = '{
    "nome": "Smartphone XYZ",
    "preco": 999.99,
    "descricao": "Smartphone último modelo"
}'
Invoke-RestMethod -Uri 'http://localhost:8100/api/products' -Method 'POST' -Headers $headers -Body $body
```

2. **Criar um Pedido**
```powershell
$headers = @{'Content-Type'='application/json'}
$body = '{
    "productId": 1,
    "customerId": 1,
    "quantity": 2,
    "totalAmount": 1999.98,
    "status": "PENDING"
}'
Invoke-RestMethod -Uri 'http://localhost:8200/api/orders' -Method 'POST' -Headers $headers -Body $body
```

## � Executando o Projeto

### Pré-requisitos
- Java 24
- Maven
- PowerShell ou Windows Terminal
- Git (opcional)

### Método Simples (Recomendado)

1. Clone ou baixe o repositório
2. Execute o script de inicialização:
```powershell
.\start-all-services.ps1
```

### Método Manual

Execute cada serviço em uma janela do PowerShell separada:

```powershell
# Terminal 1 - Eureka Server
cd eureka-server
mvn spring-boot:run

# Terminal 2 - Product Service
cd product-service
mvn spring-boot:run

# Terminal 3 - Order Service
cd order-service
mvn spring-boot:run
```

### Verificação
1. Acesse [http://localhost:8761](http://localhost:8761) para ver o Eureka Server
2. Verifique se os serviços estão registrados
3. Acesse os Swagger UIs para testar as APIs

## � API Endpoints

### Product Service (8100)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/products` | Lista todos os produtos |
| GET | `/api/products/{id}` | Busca produto por ID |
| POST | `/api/products` | Cria novo produto |
| PUT | `/api/products/{id}` | Atualiza produto |
| DELETE | `/api/products/{id}` | Remove produto |

### Order Service (8200)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/orders` | Lista todos os pedidos |
| GET | `/api/orders/{id}` | Busca pedido por ID |
| POST | `/api/orders` | Cria novo pedido |
| GET | `/api/orders/customer/{customerId}` | Lista pedidos por cliente |

## 📊 Monitoramento

### Endpoints de Monitoramento
- **Eureka:** [http://localhost:8761](http://localhost:8761)
- **Product Service Actuator:** [http://localhost:8100/actuator](http://localhost:8100/actuator)
- **Order Service Actuator:** [http://localhost:8200/actuator](http://localhost:8200/actuator)

### Métricas Disponíveis
- Status dos serviços
- Saúde da aplicação
- Informações do sistema
- Métricas de performance
- Logs em tempo real

## 📈 Próximos Passos

- [ ] Implementação de autenticação OAuth2
- [ ] Containerização com Docker
- [ ] Configuração com Kubernetes
- [ ] API Gateway com Spring Cloud Gateway
- [ ] Monitoramento avançado com Prometheus/Grafana
- [ ] Cache distribuído com Redis
- [ ] Message Broker com RabbitMQ

## 🤝 Como Contribuir

1. Fork o projeto
2. Crie sua Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes.

## ✨ Agradecimentos

- Spring Framework Team
- Netflix OSS Team
- Toda a comunidade Open Source

---

<p align="center">
  Desenvolvido por Felipe da Silva Spinola
</p>

> Uma demonstração prática e profissional de uma arquitetura de microserviços usando Spring Boot. Este projeto implementa um sistema completo de gestão de produtos e pedidos, perfeito para aprendizado e referência em desenvolvimento de microserviços.

## 📋 Índice

- [Demonstração Online](#-demonstração-online)
- [Recursos](#-recursos)
- [Arquitetura](#️-arquitetura)
- [Tecnologias](#️-tecnologias-utilizadas)
- [Exemplos de Uso](#-exemplos-de-uso)
- [Como Executar](#-como-executar)
- [Monitoramento](#-monitoramento)
- [Próximos Passos](#-próximos-passos)
- [Contribuição](#-contribuição)
- [Licença](#-licença)
- [Status do Projeto](#-status-do-projeto)
- [Contato](#-contato)

Este projeto implementa um sistema de gestão de pedidos com catálogo de produtos utilizando uma arquitetura de microsserviços com Spring Boot e Spring Cloud.

## Estrutura do Projeto

O projeto é composto por quatro serviços principais:

1. **Eureka Server** (Service Discovery)
   - Porta: 8761
   - Responsável pelo registro e descoberta de serviços

2. **API Gateway**
   - Porta: 8700
   - Gerencia o roteamento das requisições
   - Implementa autenticação via token Bearer

3. **Serviço de Produtos**
   - Porta: 8100
   - Gerenciamento do catálogo de produtos
   - Persistência com H2 Database

4. **Serviço de Pedidos**
   - Porta: 8200
   - Simulação de pedidos
   - Integração com o serviço de produtos

## Tecnologias Utilizadas

- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Spring Security
- H2 Database
- Maven

## Requisitos

- Java 17+
- Maven 3.8+

## Como Executar

1. Clone o repositório
2. Execute os serviços na seguinte ordem:
   ```bash
   # 1. Eureka Server
   cd eureka-server
   mvn spring-boot:run

   # 2. API Gateway
   cd ../api-gateway
   mvn spring-boot:run

   # 3. Serviço de Produtos
   cd ../product-service
   mvn spring-boot:run

   # 4. Serviço de Pedidos
   cd ../order-service
   mvn spring-boot:run
   ```

## Endpoints

### Serviço de Produtos
- `GET /produtos` - Lista todos os produtos
- `GET /produtos/{id}` - Busca um produto por ID
- `POST /produtos` - Cadastra um novo produto
- `PUT /produtos/{id}` - Atualiza um produto
- `DELETE /produtos/{id}` - Remove um produto

### Serviço de Pedidos
- `GET /pedidos/produtos` - Lista produtos disponíveis
- `POST /pedidos/simular` - Simula um pedido

## Autenticação

Todas as requisições devem incluir um token Bearer no header Authorization:

```
Authorization: Bearer seu-token-aqui
```

## Documentação Adicional

- [Swagger UI - Serviço de Produtos](http://localhost:8100/swagger-ui.html)
- [Swagger UI - Serviço de Pedidos](http://localhost:8200/swagger-ui.html)
- [Eureka Dashboard](http://localhost:8761)