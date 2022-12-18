package com.proj.restapi.service;

import java.util.List;

public interface ServiceInterface<T>{

    public List<T> findAll();

    public T findById(long id);

    public T save(T t);

    public void delete(long id);
}
