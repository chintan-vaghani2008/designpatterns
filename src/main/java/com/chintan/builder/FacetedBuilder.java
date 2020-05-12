package com.chintan.builder;

import lombok.Data;

public class FacetedBuilder {
    public static void main(String arg[]) {
        Person person =
                Person.builder()
                    .setName("Mike")
                    .setAge(26)
                    .address()
                        .setCity("Surat")
                        .setCountry("India")
                        .setPincode(560102)
                        .buildAddress()
                    .worksAt()
                        .setCompany("XYZ")
                        .setLocation("Bangalore")
                        .buildWork()
                    .build();
        System.out.println(person);
    }

    @Data
    static class Person {
        private String name;
        private int age;

        // address info
        private String city;
        private String country;
        private int pincode;

        // work info
        private String company;
        private String location;

        public static PersonBuilder builder() {
            return new PersonBuilder();
        }


        static class PersonBuilder {
            private Person person = new Person();
            private String name;
            private int age;


            public PersonBuilder setName(String name) {
                this.name = name;
                return this;
            }

            public PersonBuilder setAge(int age) {
                this.age = age;
                return this;
            }

            public AddressBuilder address() {
                return new AddressBuilder(person, this);
            }

            public WorkBuilder worksAt() {
                return new WorkBuilder(person, this);
            }

            public Person build() {
                person.setName(name);
                person.setAge(age);
                return person;
            }
        }

        static class AddressBuilder {
            private PersonBuilder personBuilder;
            private Person person;
            private String city;
            private String country;
            private int pincode;

            AddressBuilder(Person person, PersonBuilder personBuilder) {
                this.personBuilder = personBuilder;
                this.person = person;
            }

            public AddressBuilder setCity(String city) {
                this.city = city;
                return this;
            }

            public AddressBuilder setCountry(String country) {
                this.country = country;
                return this;
            }

            public AddressBuilder setPincode(int pincode) {
                this.pincode = pincode;
                return this;
            }

            public PersonBuilder buildAddress() {
                person.setCity(this.city);
                person.setCountry(this.country);
                person.setPincode(this.pincode);
                return personBuilder;
            }
        }

        static class WorkBuilder {
            private PersonBuilder personBuilder;
            private Person person;
            private String company;
            private String location;

            WorkBuilder(Person person, PersonBuilder personBuilder) {
                this.personBuilder = personBuilder;
                this.person = person;
            }

            public WorkBuilder setCompany(String company) {
                this.company = company;
                return this;
            }

            public WorkBuilder setLocation(String location) {
                this.location = location;
                return this;
            }

            public PersonBuilder buildWork() {
                person.setCompany(this.company);
                person.setLocation(this.location);
                return personBuilder;
            }
        }
    }

}
