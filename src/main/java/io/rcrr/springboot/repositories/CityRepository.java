package io.rcrr.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.rcrr.springboot.domains.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
