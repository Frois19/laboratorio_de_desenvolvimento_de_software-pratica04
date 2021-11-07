package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Coins;
import br.com.springboot.repository.CoinsRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/coins")
public class CoinsController {
    
    @Autowired
    private CoinsRepository coinsRepository;


    @PostMapping()
    public Coins postCoins(@RequestBody Coins Coins) {
        return this.coinsRepository.save(Coins);
    }

    @GetMapping("/{id}")
    public Coins getCoins(@PathVariable("id") Long id) {

        Optional<Coins> requestFind = this.coinsRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Coins> listCoins() {
        return this.coinsRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteCoins(@PathVariable("id") Long id) {

        Optional<Coins> requestFind = this.coinsRepository.findById(id);

        if (requestFind.isPresent()) {
            this.coinsRepository.deleteById(id);
        }
    }
}
