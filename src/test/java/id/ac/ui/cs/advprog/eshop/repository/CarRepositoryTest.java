package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateWithNullId() {
        Car car = new Car();
        car.setCarName("Toyota C-HR");
        car.setCarColor("Black");
        car.setCarQuantity(2);

        // Mengetes blok if (car.getCarId() == null)
        Car savedCar = carRepository.create(car);

        assertNotNull(savedCar.getCarId());
        assertEquals("Toyota C-HR", savedCar.getCarName());
    }

    @Test
    void testCreateWithExistingId() {
        Car car = new Car();
        UUID existingId = UUID.randomUUID();
        car.setCarId(existingId);
        car.setCarName("Honda Civic");

        // Mengetes ketika if (car.getCarId() == null) dilewati
        Car savedCar = carRepository.create(car);

        assertEquals(existingId, savedCar.getCarId());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        carRepository.create(car1);

        Car car2 = new Car();
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();

        assertTrue(carIterator.hasNext());
        carIterator.next();
        assertTrue(carIterator.hasNext());
        carIterator.next();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindByIdIfFound() {
        Car car = new Car();
        car.setCarName("Suzuki Swift");
        Car savedCar = carRepository.create(car);

        // Mengetes blok for loop dan return car
        Car foundCar = carRepository.findById(savedCar.getCarId());

        assertNotNull(foundCar);
        assertEquals(savedCar.getCarId(), foundCar.getCarId());
        assertEquals("Suzuki Swift", foundCar.getCarName());
    }

    @Test
    void testFindByIdIfNotFound() {
        // Mengetes blok return null di findById
        UUID randomId = UUID.randomUUID();
        Car foundCar = carRepository.findById(randomId);

        assertNull(foundCar);
    }

    @Test
    void testUpdateIfFound() {
        Car originalCar = new Car();
        originalCar.setCarName("Mitsubishi Pajero");
        originalCar.setCarColor("White");
        originalCar.setCarQuantity(5);
        Car savedCar = carRepository.create(originalCar);

        Car updatedCarData = new Car();
        updatedCarData.setCarName("Mitsubishi Pajero Sport");
        updatedCarData.setCarColor("Black");
        updatedCarData.setCarQuantity(10);

        // Mengetes blok update setter dan return car
        Car resultCar = carRepository.update(savedCar.getCarId(), updatedCarData);

        assertNotNull(resultCar);
        assertEquals("Mitsubishi Pajero Sport", resultCar.getCarName());
        assertEquals("Black", resultCar.getCarColor());
        assertEquals(10, resultCar.getCarQuantity());
    }

    @Test
    void testUpdateIfNotFound() {
        Car updatedCarData = new Car();
        updatedCarData.setCarName("Ghost Car");

        // Mengetes blok return null di update
        UUID randomId = UUID.randomUUID();
        Car resultCar = carRepository.update(randomId, updatedCarData);

        assertNull(resultCar);
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarName("Nissan GTR");
        Car savedCar = carRepository.create(car);

        // Pastikan mobil ada sebelum dihapus
        assertNotNull(carRepository.findById(savedCar.getCarId()));

        // Mengetes blok removeIf
        carRepository.delete(savedCar.getCarId());

        // Pastikan mobil sudah hilang
        assertNull(carRepository.findById(savedCar.getCarId()));
    }
}