package com.example.cdcconsumerdemo.message;

public interface PersonRepository {

    Person findPersonById(Long id);

    class Person {
        Long id;
        String name;
        String surname;

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }
}
