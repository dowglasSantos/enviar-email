package com.app.service;

import com.app.dto.ClienteRequestDTO;
import com.app.entity.Cliente;
import com.app.repository.IClienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private IClienteRepository clienteRepository;
    private EmailService emailService;
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.name.exchangename}")
    private String exchange;

    @Value("${rabbitmq.name.routingkey}")
    private String routingKey;

    @Autowired
    public ClienteService(IClienteRepository clienteRepository, EmailService emailService, RabbitTemplate rabbitTemplate) {
        this.clienteRepository = clienteRepository;
        this.emailService = emailService;
        this.rabbitTemplate = rabbitTemplate;

    }

    public Cliente salvarCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.nome());
        cliente.setEmail(clienteRequestDTO.email());

        emailService.enviarEmail(
                clienteRequestDTO.email(),
                "Bem-vindo!!" + clienteRequestDTO.nome(),
                "Conta criada com sucesso!"
        );

        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                clienteRequestDTO.email());

        return  clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Cliente não encontrado.");
        });
    }
}
