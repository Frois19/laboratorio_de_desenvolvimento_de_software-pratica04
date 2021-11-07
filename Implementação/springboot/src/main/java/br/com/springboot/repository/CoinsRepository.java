package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Coins;

public interface CoinsRepository  extends JpaRepository<Coins, Long> {
    
}
