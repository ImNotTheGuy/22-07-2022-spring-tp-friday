package com.example.friday.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friday.dao.PlayerRepository;
import com.example.friday.entity.Player;

@Service
public class PlayerService implements ServiceInterface<Player> {

    @Autowired
    PlayerRepository playerRepository;

    public void create(Player player) {
        playerRepository.save(player);
    }

    public void update(long id, Player player) {
        Player updatePlayer = findById(id);
        updatePlayer.setScore(player.getScore());
        updatePlayer.setUsername(player.getUsername());
        playerRepository.save(player);

    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(long id) {
        return playerRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        playerRepository.deleteById(id);

    }

}
