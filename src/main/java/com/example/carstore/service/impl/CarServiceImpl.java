package com.example.carstore.service.impl;

import com.example.carstore.model.Car;
import com.example.carstore.service.GenericService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CarServiceImpl implements GenericService<Car> {
    
    private final Map<Long, Car> carDatabase = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public CarServiceImpl() {
        // Demonstração
        Car car1 = new Car(1L, "Toyota", "Corolla", 2022, new BigDecimal("85000.00"), "Prata");
        Car car2 = new Car(2L, "Honda", "Civic", 2023, new BigDecimal("95000.00"), "Preto");
        Car car3 = new Car(3L, "Volkswagen", "Jetta", 2021, new BigDecimal("78000.00"), "Branco");
        Car car4 = new Car(4L, "Ford", "Fusion", 2020, new BigDecimal("72000.00"), "Azul");
        Car car5 = new Car(5L, "Chevrolet", "Cruze", 2022, new BigDecimal("82000.00"), "Vermelho");
        
        carDatabase.put(1L, car1);
        carDatabase.put(2L, car2);
        carDatabase.put(3L, car3);
        carDatabase.put(4L, car4);
        carDatabase.put(5L, car5);
        
        idGenerator.set(6L);
    }
    
    @Override
    public Car save(Car car) {
        if (car.getId() == null) {
            car.setId(idGenerator.getAndIncrement());
        }
        carDatabase.put(car.getId(), car);
        return car;
    }
    
    @Override
    public List<Car> findAll() {
        return new ArrayList<>(carDatabase.values());
    }
    
    @Override
    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(carDatabase.get(id));
    }
    
    @Override
    public Car update(Car car) {
        if (car.getId() != null && carDatabase.containsKey(car.getId())) {
            carDatabase.put(car.getId(), car);
            return car;
        }
        throw new RuntimeException("Carro não encontrado com ID: " + car.getId());
    }
    
    @Override
    public boolean deleteById(Long id) {
        return carDatabase.remove(id) != null;
    }
    
}