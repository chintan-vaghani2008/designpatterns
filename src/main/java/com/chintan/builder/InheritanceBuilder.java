package com.chintan.builder;

import lombok.Data;

public class InheritanceBuilder {

    public static void main(String arg[]) {
        Person person = new CompanyBuilder()
                    .setName("Chintan")
                    .setCompany("XYZ")
                    .build();
        System.out.println(person);
    }

    @Data
    static class Person {
        private String name;
        private String company;
    }

    static class PersonBuilder<SELF extends PersonBuilder<SELF>>{
        protected Person person = new Person();
        private String name;

        public SELF setName(String name) {
            person.name = name;
            return self();
        }

        protected SELF self() {
            return (SELF)this;
        }

        public Person build() {
            return person;
        }
    }

    static class CompanyBuilder extends PersonBuilder<CompanyBuilder>{
        public CompanyBuilder setCompany(String company) {
            person.company = company;
            return self();
        }

        @Override
        protected CompanyBuilder self() {
            return this;
        }
    }
}



