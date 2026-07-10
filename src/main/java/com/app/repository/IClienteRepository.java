package com.app.repository;

import com.app.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
