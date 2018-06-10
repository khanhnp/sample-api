package com.spsvn.api.domain.repository.jpa;

import com.spsvn.api.domain.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
}
