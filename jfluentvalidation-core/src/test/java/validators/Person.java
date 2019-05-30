package validators;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {

    private String name;
    private int age;
    private Address address;
    private boolean isMarried;
    private ZonedDateTime dob;
    private ZonedDateTime signedIn = ZonedDateTime.now();
    private List<String> children = new ArrayList<>();
    private Map<String, String> pets = new HashMap<>();
    private byte[] bytes = new byte[0];

    public Person() {}

    public Person(String name, int age, Address address) {
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

    public Address getAddress() {
        return address;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public ZonedDateTime getDob() {
        return dob;
    }

    public void setDob(ZonedDateTime dob) {
        this.dob = dob;
    }

    public ZonedDateTime getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(ZonedDateTime signedIn) {
        this.signedIn = signedIn;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public Map<String, String> getPets() {
        return pets;
    }

    public void setPets(Map<String, String> pets) {
        this.pets = pets;
    }

    public byte[] getBytes() {
        return bytes;
    }

}
