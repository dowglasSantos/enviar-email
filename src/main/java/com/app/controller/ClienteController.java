package com.app.controller;

import com.app.dto.ClienteRequestDTO;
import com.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public void salvarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        clienteService.salvarCliente(clienteRequestDTO);
    }

    @GetMapping("/{id}")
    public void buscarClientePorId(@PathVariable Integer id) {
        clienteService.buscarClientePorId(id);
    }
}
