package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
