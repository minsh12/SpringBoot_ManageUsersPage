package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    // first business method: listing all users in the DB
    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {       // user가 있는지 체크
            return result.get();
        }
        // user가 없으면 아래의 예외클래스 실행
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        // delete하기전 id가 있는지 확인
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            // id가 없으면 아래의 예외클래스 실행
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }

        repo.deleteById(id);
    }
}
