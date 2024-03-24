package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
}
