package com.example.springfling.Repository;

import com.example.springfling.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User findByUserId(Long id);


}