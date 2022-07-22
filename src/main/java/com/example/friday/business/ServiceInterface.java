package com.example.friday.business;

import java.util.List;

public interface ServiceInterface<T> {

    // Create
    // public void create(T entity);

    // Read - all
    public List<T> findAll();

    // Read - one
    public T findById(long id);

    // Update
    // public void update(T entity);

    // Delete
    public void deleteById(long id);
}
