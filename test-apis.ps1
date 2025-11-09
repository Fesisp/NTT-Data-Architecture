# Criar um produto
curl -X POST http://localhost:8081/api/products `
  -H "Content-Type: application/json" `
  -d "{\"nome\":\"Smartphone\",\"descricao\":\"iPhone 15\",\"preco\":5999.99}"

# Listar produtos
curl http://localhost:8081/api/products

# Criar um pedido
curl -X POST http://localhost:8082/api/orders `
  -H "Content-Type: application/json" `
  -d "{\"productId\":1,\"customerId\":1,\"quantity\":2}"

# Listar pedidos
curl http://localhost:8082/api/orders

# Verificar status do Eureka
curl http://localhost:8761/eureka/apps