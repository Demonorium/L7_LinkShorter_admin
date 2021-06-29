package com.demonorium.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends CrudRepository<UrlEntity, Long> {
    List<UrlEntity> getByInput(String input);
}
