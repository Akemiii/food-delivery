package org.example.util;

public enum OrderStatus {
    PEENDING, // pedido realizado, aguardando restaurante aceitar
    ACCEPTED, // pedido aceito pelo restaurante, pedido em preparação
    PREPARED, // pedido finalizado, disponível para entrega
    DELIVERY, // item saiu para a entrega
    CONFIRMED, // pedido foi entregue para o cliente
    REJECTED, // pedido rejeitado pelo restaurante
    CANCEL_PENDING, // Solicitado o cancelamento do pedido pelo cliente
    CANCELLED //Pedido cancelado
}