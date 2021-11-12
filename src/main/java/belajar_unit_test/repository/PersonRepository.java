package belajar_unit_test.repository;

import belajar_unit_test.data.Person;

public interface PersonRepository {

    Person selectById(String id);

    void insert(Person person);

}
