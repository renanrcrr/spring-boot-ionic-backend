package io.rcrr.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.rcrr.springboot.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
