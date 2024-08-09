package io.rcrr.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.rcrr.springboot.domains.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
