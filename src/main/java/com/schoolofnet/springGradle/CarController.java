package com.schoolofnet.springGradle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
	@Autowired
	private CarRepository carRepository;
	
	public CarController(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@GetMapping
	@ResponseBody
	public List<Car> findAll() {
		return this.carRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Car> findById(@PathVariable("id") Long id) {
		return this.carRepository.findById(id);
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Car create(@RequestBody Car car) {
		return this.carRepository.save(car);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void removeById(@PathVariable("id") Long id) {
		this.carRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void updateById(@PathVariable("id") Long id, @RequestBody Car car) {
		this.carRepository.findById(id).map(record -> {
			record.setName(car.getName());
			record.setColor(car.getColor());
			record.setYear(car.getYear());	
			Car updated = this.carRepository.save(record);
			return updated;
		});
	}
}