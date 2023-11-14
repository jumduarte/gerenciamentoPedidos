package com.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gerenciamento.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}