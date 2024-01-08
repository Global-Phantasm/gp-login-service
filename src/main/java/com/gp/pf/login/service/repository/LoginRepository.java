package com.gp.pf.login.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gp.pf.login.service.model.Login;

public interface LoginRepository extends MongoRepository<Login, String>{

}
