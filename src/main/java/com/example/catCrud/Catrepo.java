package com.example.catCrud;

import org.springframework.data.repository.CrudRepository;

public interface Catrepo extends CrudRepository<Cat, Long> {
}
