package io.rcrr.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.rcrr.springboot.domains.ItemOrder;
import io.rcrr.springboot.domains.ItemOrderPK;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, ItemOrderPK>{

}
