package com.example.sharded_saga_wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sharded_saga_wallet.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findByNameContainingIgnoreCase(String name);

//    @Query(
//            value = "SELECT u.* FROM users u WHERE UPPER(u.name) LIKE UPPER(:pattern)",
//            nativeQuery = true
//    )
//    List<User> searchByName(@Param("pattern") String pattern);
}
