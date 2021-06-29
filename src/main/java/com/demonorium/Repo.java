package com.demonorium;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo extends CrudRepository<UrlInfo, Long> {
    List<UrlInfo> getByInput(String input);
}
