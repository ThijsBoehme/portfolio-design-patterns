package structural_patterns.proxy;

class Person2 {
    private int age;

    public Person2(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String drink() {
        return "drinking";
    }

    public String drive() {
        return "driving";
    }

    public String drinkAndDrive() {
        return "driving while drunk";
    }
}

class ResponsiblePerson {
    private Person2 person;

    public ResponsiblePerson(Person2 person) {
        this.person = person;
    }

    public int getAge() {
        return person.getAge();
    }

    public void setAge(int age) {
        person.setAge(age);
    }

    public String drink() {
        if (person.getAge() < 18) return "too young";
        return "drinking";
    }

    public String drive() {
        if (person.getAge() < 16) return "too young";
        return "driving";
    }

    public String drinkAndDrive() {
        return "dead";
    }
}
