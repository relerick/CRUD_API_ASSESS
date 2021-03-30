package com.example.catCrud;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")

public class CatController {


    private final Catrepo repository;


    public CatController(Catrepo repository) {
        this.repository = repository;
    }

    @PostMapping("/cat")
    public Cat create(@RequestBody Cat cat) {
        return this.repository.save(cat);


    }

    @GetMapping("/cat/{id}")
    public Optional<Cat> show(@PathVariable Long id) {
        return this.repository.findById(id);


    }

    @GetMapping("/cat")
    public Iterable<Cat> index() {
        return this.repository.findAll();

    }
    @DeleteMapping("/cat/{id}")
    private void delete(@PathVariable long id){
        this.repository.deleteById(id);
    }



    @PatchMapping("/cat/{id}")
    public  Cat update(@RequestBody Cat cat, @PathVariable Long id){
        if (this.repository.existsById(id)){
            Cat updateCat = this.repository.findById(id).get();
            updateCat.setBreed(cat.getBreed());
            updateCat.setName(cat.getName());
            this.repository.save(updateCat);

        }
        else {
            return this.repository.save(cat);
        }

        return cat;
    }


}



