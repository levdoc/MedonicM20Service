package com.levdoc.m20service.repository;

import com.levdoc.m20service.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends GenericRepository<UserModel>{
    UserModel findUserModelByLogin(String login);
    UserModel findUserModelById(Long id);
}
