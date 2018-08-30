import java.lang.reflect.Array;
import java.util.*;

public class MainAPICollection {

    public static void main(String[] args) {

        Person p1 = new Person("Linda", 24);
        Person p2 = new Person("Daniel", 28);
        Person p3 = new Person("Mercy", 47);
        Person p4 = new Person("Selva", 46);
        Person p5 = new Person("Beni", 18);

        List<Person> people = new ArrayList(Arrays.asList(p1, p2, p3, p4, p5));
        people.forEach(System.out::println);
        System.out.println("************");
        people.removeIf(person -> person.getAge() < 25);
        people.forEach(System.out::println);
        System.out.println("************");
        people.replaceAll(person -> new Person(person.getName().toUpperCase(), person.getAge()));
        people.forEach(System.out::println);
        System.out.println("************");
        people.sort(Comparator.comparing(Person::getAge).reversed());
        people.forEach(System.out::println);


        System.out.println("************");
        City london = new City("London");
        City bangalore = new City("Bangalore");
        City paris = new City("Paris");

        Map<City, List<Person>> map = new HashMap<>();
        System.out.println("People from Paris :" + map.getOrDefault(paris, Collections.EMPTY_LIST));

        System.out.println("************");

        map.putIfAbsent(paris, new ArrayList<>());
        map.get(paris).add(p1);
        System.out.println("People from Paris :" + map.getOrDefault(paris, Collections.EMPTY_LIST));

        System.out.println("************");

        map.computeIfAbsent(london, city -> new ArrayList<>()).add(p2);
        map.computeIfAbsent(london, city -> new ArrayList<>()).add(p3);
        System.out.println("People from London :" + map.getOrDefault(london, Collections.EMPTY_LIST));
        System.out.println("***************************");

        Map<City, List<Person>> map1 = new HashMap<>();
        map1.computeIfAbsent(london, city -> new ArrayList<>()).add(p1);
        map1.computeIfAbsent(bangalore, city -> new ArrayList<>()).add(p2);
        map1.computeIfAbsent(bangalore, city -> new ArrayList<>()).add(p3);

        System.out.println("Map 1");
        map1.forEach((city, ppl) -> System.out.println(city + " : " + ppl));

        Map<City, List<Person>> map2 = new HashMap<>();
        map2.computeIfAbsent(bangalore, city -> new ArrayList<>()).add(p4);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p5);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p4);

        System.out.println("Map 2");
        map2.forEach((city, ppl) -> System.out.println(city + " : " + ppl));

        System.out.println("***************************");

        map2.forEach(
                (city, ppl) -> {
                    map1.merge(
                            city, ppl,
                                    (peopleFromMap1, peopleFromMap2) -> {
                        peopleFromMap1.addAll(peopleFromMap2);
                        return peopleFromMap1;
                    });

                }
        );


        System.out.println("Merged map1 ");
        map1.forEach(
                ((city, people1) -> System.out.println(city+ " : "+people1))
        );
    }
    }

