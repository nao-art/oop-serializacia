package oop;

import java.io.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        serialize();
        deserialize();
    }

    public static void serialize(){
        Showcase[] obj = new Showcase[]{new Showcase("Jozko", 14, "nbusr123")};

        try {
            FileOutputStream file = new FileOutputStream("ShowcaseDump.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(obj);
            out.close();
            file.close();
            System.out.println("Object serialized");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Object not serialized");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Object not serialized");
        }

        System.out.println("Serialization:");
        for (Showcase showcase : obj) {
            showcase.sayHello();
        }
    }

    public static void deserialize(){
        Showcase[] showcases = null;
        try {
            FileInputStream file = new FileInputStream("ShowcaseDump.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            showcases = (Showcase[]) in.readObject();

            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Deserialization:");

        for (int i = 0; i < showcases.length; i++){
            Showcase showcase = showcases[i];
            showcase.sayHello();
        }

        for (Showcase showcase : showcases) {
            showcase.sayHello();
        }

        Arrays.stream(showcases).forEach(new Consumer<Showcase>() {
            @Override
            public void accept(Showcase showcase) {
                showcase.sayHello();
            }
        });

        Arrays.stream(showcases).forEach(showcase -> showcase.sayHello());

        Arrays.stream(showcases).forEach(Showcase::sayHello);
    }
}

class Showcase implements Serializable {
    static int population = 4;
    String name;
    int age;
    transient String password;

    public Showcase(String name, int age, String password){
        population = population + 1;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public void sayHello(){
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Password: " + this.password);
        System.out.println("Population: " + population);
        System.out.println("-----------------------");
    }
}
