package io.rcrr.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.rcrr.springboot.domains.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
