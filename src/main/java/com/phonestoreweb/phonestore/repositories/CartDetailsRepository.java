package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails,Long> {
}
