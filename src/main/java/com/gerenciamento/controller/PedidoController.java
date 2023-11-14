package com.gerenciamento.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.entities.Pedido;
import com.gerenciamento.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedidos", description = "API REST DE GERENCIAMENTO DE PEDIDOS")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    private final PedidoService pedidoService;
    
    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza Pedido por ID")
    public ResponseEntity<Pedido> getProductById(@PathVariable Long id) {
    	Pedido pedido = pedidoService.getPedidoById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os Pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedido();
        return ResponseEntity.ok(pedidos);
    }
    @PostMapping("/")
    @Operation(summary = "Cadastra um Pedido")
    public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid Pedido pedido) {
    	Pedido criarPedido = pedidoService.salvarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarPedido);
    }
   

    @PutMapping("/{id}")
    @Operation(summary = "Altera o Pedido")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
    	Pedido updatedPedido = pedidoService.updatePedido(id, pedido);
        if (updatedPedido != null) {
            return ResponseEntity.ok(updatedPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o Pedido")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        boolean deleted = pedidoService.deletePedido(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}