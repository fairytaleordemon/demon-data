package com.yuanbosu.data.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public abstract interface BaseRepository<T> extends MongoRepository<T, String> {
}