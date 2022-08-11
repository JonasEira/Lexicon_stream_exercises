package se.lexicon.vxo.service;

import se.lexicon.vxo.data.JsonReader;
import se.lexicon.vxo.model.Gender;
import se.lexicon.vxo.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PeopleImpl implements People {

    private static final PeopleImpl INSTANCE;

    static {
        INSTANCE = new PeopleImpl();
    }

    public static PeopleImpl getInstance(){
        return INSTANCE;
    }

    private List<Person> people;

    private PeopleImpl(){
        people = JsonReader.getInstance().read();
    }

    @Override
    public List<Person> getPeople(){
        return people;
    }


    public static List<Person> filterFirstName(String firstName) {
        List<Person> contains = PeopleImpl.getInstance().getPeople().stream().filter(
                p -> p.getFirstName().equals(firstName)).collect(Collectors.toList()
        );

        return contains;
    }

    public static List<Person> filterLastName(String lastName) {
        List<Person> contains = PeopleImpl.getInstance().getPeople().stream().filter(
                p -> p.getLastName().equals(lastName)).collect(Collectors.toList()
        );

        return contains;
    }


    public static List<Person> filterGender(Gender gender) {
        List<Person> contains = PeopleImpl.getInstance().getPeople().stream().filter(
                p -> p.getGender().equals(gender)).collect(Collectors.toList()
        );

        return contains;
    }

    public static TreeSet<LocalDate> getBirthDates() {
        List<Person> contains = PeopleImpl.getInstance().getPeople().stream().filter(
                p -> p.getDateOfBirth().isBefore(LocalDate.now())).collect(Collectors.toList()
        );
        TreeSet<LocalDate> birthDates = new TreeSet<>();
        for(Person p : contains){
            birthDates.add(p.getDateOfBirth());
        }
        return birthDates;
    }
}
