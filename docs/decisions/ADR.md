# 1. Arquitetura de Microsserviços com Spring Cloud

Data: 2025-11-08

## Status

Aceito

## Contexto

O sistema precisa gerenciar um catálogo de produtos e simular pedidos de forma escalável e resiliente.

## Decisão

Implementar uma arquitetura de microsserviços usando Spring Boot e Spring Cloud com os seguintes componentes:

1. Eureka Server para service discovery
2. API Gateway para roteamento e segurança
3. Serviço de Produtos com banco H2
4. Serviço de Pedidos com integração via Feign

## Consequências

### Positivas
- Escalabilidade independente dos serviços
- Facilidade de manutenção
- Resiliência com Circuit Breaker
- Descoberta dinâmica de serviços

### Negativas
- Complexidade aumentada
- Necessidade de monitoramento distribuído
- Latência adicional devido à comunicação entre serviços

# 2. Escolha do Banco de Dados H2

Data: 2025-11-08

## Status

Aceito

## Contexto

O serviço de produtos precisa persistir dados do catálogo.

## Decisão

Usar H2 Database em modo embedded por:
1. Simplicidade de configuração
2. Adequado para demonstração
3. Não requer instalação externa

## Consequências

### Positivas
- Rápida configuração
- Bom para desenvolvimento e testes
- Portabilidade

### Negativas
- Não adequado para produção
- Dados não persistentes entre reinicializações

# 3. Implementação de Segurança

Data: 2025-11-08

## Status

Aceito

## Contexto

Necessidade de proteger os endpoints da API.

## Decisão

Implementar autenticação via token Bearer no API Gateway com:
1. Validação de token
2. Rate limiting
3. CORS configurável

## Consequências

### Positivas
- Segurança centralizada
- Controle de acesso uniforme
- Fácil integração com outros sistemas

### Negativas
- Overhead de processamento no gateway
- Ponto único de falha para autenticação

# 4. Monitoramento e Observabilidade

Data: 2025-11-08

## Status

Aceito

## Contexto

Necessidade de monitorar e diagnosticar problemas no sistema distribuído.

## Decisão

Implementar stack de monitoramento com:
1. Prometheus + Grafana para métricas
2. Zipkin para tracing distribuído
3. ELK Stack para logs

## Consequências

### Positivas
- Visibilidade completa do sistema
- Diagnóstico rápido de problemas
- Análise de performance

### Negativas
- Recursos adicionais necessários
- Complexidade de configuração
- Overhead de coleta de dados