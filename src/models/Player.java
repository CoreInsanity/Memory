package models;

/**
 * Maxim Van den Eede
 * 13/12/2019.
 */
public class Player {
    private String Name;
    private int Age;

    // Getters
    public String getName() {
        return Name;
    }
    public int getAge() {
        return Age;
    }

    // Setters
    public void setName(String name){
        Name = name;
    }
    public void setAge(int age){
        Age = age;
    }
}
