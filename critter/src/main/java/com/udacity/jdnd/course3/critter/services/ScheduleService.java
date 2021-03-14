package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAllSchedulesByPetId(Long id){
        return scheduleRepository.findAllByPetsContaining(petRepository.getOne(id));
    }

    public List<Schedule> getAllSchedulesByEmployeeId(Long id){
        return scheduleRepository.findAllByEmployeeContaining(employeeRepository.getOne(id));
    }

    public List<Schedule> getAllSchedulesByCustomerId(Long id){
        List<Pet> pets = customerRepository.getOne(id).getPets();
        List<Schedule> schedules = new ArrayList<>();
        for(Pet pet : pets){
            schedules.addAll(scheduleRepository.findAllByPetsContaining(pet));
        }
        return schedules;
    }
}
