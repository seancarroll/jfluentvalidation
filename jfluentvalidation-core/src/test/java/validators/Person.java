package validators;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Person {

    private String name;
    private int age;
    private String address;
    private boolean isMarried;
    private ZonedDateTime signedIn = ZonedDateTime.now();
    private Map<String, String> pets = new HashMap<>();

    public Person() {}

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public ZonedDateTime getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(ZonedDateTime signedIn) {
        this.signedIn = signedIn;
    }

    public Map<String, String> getPets() {
        return pets;
    }

    public void setPets(Map<String, String> pets) {
        this.pets = pets;
    }
}
