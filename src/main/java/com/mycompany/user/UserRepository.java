package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;

// to use spring data JPA
public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
