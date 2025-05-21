package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projeto.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // você pode declarar queries customizadas aqui, se precisar
}