package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
