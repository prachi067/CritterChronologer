package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;


    public Pet savePet(Pet pet) {

        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getCustomer();
        List<Pet> customerPets = customer.getPets();
        if(customerPets == null){
            customerPets = new ArrayList<>();
        }
        customerPets.add(savedPet);
        customer.setPets(customerPets);
        customerRepository.save(customer);
        return savedPet;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPet(Long id) {
        return petRepository.getOne(id);
    }

    public List<Pet> getPetsByOwnerId(Long id){
        List<Pet> pets;

        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            pets = customer.getPets();
        } else {
            pets = new ArrayList<>();
        }

        return pets;
    }

    public List<Pet> findPetsByid(List<Long> id){
        return petRepository.findAllById(id);
    }


}
