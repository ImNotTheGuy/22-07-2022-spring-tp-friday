package com.example.friday.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.friday.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>  {
    
}
