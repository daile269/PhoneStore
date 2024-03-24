package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
