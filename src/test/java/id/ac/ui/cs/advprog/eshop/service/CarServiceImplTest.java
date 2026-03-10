package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;
    private UUID carId;

    @BeforeEach
    void setUp() {
        // Setup data dummy sebelum setiap test dijalankan
        carId = UUID.randomUUID();
        car = new Car();
        car.setCarId(carId);
        car.setCarName("Toyota Yaris");
        car.setCarColor("Yellow");
        car.setCarQuantity(3);
    }

    @Test
    void testCreate() {
        when(carRepository.create(car)).thenReturn(car);

        Car savedCar = carService.create(car);

        assertNotNull(savedCar);
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals("Toyota Yaris", savedCar.getCarName());

        // Memastikan method create pada repository dipanggil tepat 1 kali
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAll() {
        // Menyiapkan iterator dummy
        List<Car> dummyCarList = new ArrayList<>();
        dummyCarList.add(car);

        Car car2 = new Car();
        car2.setCarId(UUID.randomUUID());
        car2.setCarName("Honda Jazz");
        dummyCarList.add(car2);

        Iterator<Car> iterator = dummyCarList.iterator();

        // Mocking findAll agar mengembalikan iterator dummy kita
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> resultList = carService.findAll();

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(car.getCarId(), resultList.get(0).getCarId());

        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById(carId)).thenReturn(car);

        Car foundCar = carService.findById(carId);

        assertNotNull(foundCar);
        assertEquals(carId, foundCar.getCarId());
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    void testUpdate() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Yaris Facelift");

        // Memanggil method yang di-test
        carService.update(carId, updatedCar);

        // Verifikasi bahwa update di repository benar-benar dipanggil dengan parameter yang sesuai
        verify(carRepository, times(1)).update(carId, updatedCar);
    }

    @Test
    void testDeleteCarById() {
        // Memanggil method yang di-test
        carService.deleteCarById(carId);

        // Verifikasi bahwa delete di repository benar-benar dipanggil dengan ID yang sesuai
        verify(carRepository, times(1)).delete(carId);
    }
}