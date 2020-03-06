package com.example.db.repositories;

import com.example.db.dao.CarDao;
import com.example.db.models.Car;
import com.example.db.models.User;

import java.util.List;
import java.util.Optional;

public class CarDaoJdbcImpl implements CarDao {
    @Override
    public List<Car> findByModel(String model) {
        return null;
    }

    @Override
    public List<Car> findByUser(User user) {
        return null;
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Car model) {

    }

    @Override
    public void update(Car model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Car> findAll() {
        return null;
    }
}
