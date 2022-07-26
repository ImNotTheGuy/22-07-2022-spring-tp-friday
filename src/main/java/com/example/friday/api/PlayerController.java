package com.example.friday.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friday.business.PlayerService;
import com.example.friday.entity.Player;


@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/player/")
    public void create(@RequestBody Player player) {
        System.out.println(player);
        playerService.create(player);
    }

    @GetMapping("/player/")
    public List<Player> getAll(Player player) {
        return playerService.findAll();
    }

    @GetMapping("/player/{id}")
    public void getById(@PathVariable long id) {
        playerService.findById(id);
    }

    @PatchMapping("/player/{id}")
    public void update(long id, @RequestBody Player player) {
        playerService.update(id, player);
    }

    @DeleteMapping("/player/{id}")
    public void deleteById(long id) {
        playerService.deleteById(id);
    }
}
