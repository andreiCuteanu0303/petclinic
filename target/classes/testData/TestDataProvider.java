package com.endava.petclinic.testData;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestDataProvider {

    private Faker faker = new Faker();

    public Owner getOwner(){
        var owner = new Owner(); // Replaced Owner cu var.
                                 // Ex. of why: Map<String, String> map = new HashMap<>() into var map = new Hashmap<String, String>();

        owner.setFirstName(faker.name().firstName());
        owner.setLastName(faker.name().lastName());
        owner.setAddress(faker.address().fullAddress());
        owner.setCity(faker.address().city());
        owner.setTelephone(faker.number().digits(faker.number().numberBetween(7, 9)));

        return owner;
    }

    public String getNumberWithDigits(int min, int max){
        return faker.number().digits(faker.number().numberBetween(min, max));
    }

    public Pet getPet(Owner owner, PetType type){

        String birthdate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        Pet pet = new Pet();
        pet.setName(faker.artist().name());
        pet.setBirthDate(birthdate);
        pet.setOwner(owner);
        pet.setType(type);

        return pet;
    }
}
