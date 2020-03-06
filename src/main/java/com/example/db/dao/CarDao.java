package com.example.db.dao;

import com.example.db.models.Car;
import com.example.db.models.User;

import java.util.List;

public interface CarDao extends CrudDao<Car> {
    List<Car> findByModel(String model);

    List<Car> findByUser(User user);
}
