package se.lexicon.vxo.service;

import se.lexicon.vxo.data.JsonReader;
import se.lexicon.vxo.model.Gender;
import se.lexicon.vxo.model.Person;
import se.lexicon.vxo.model.PersonDto;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PeopleImpl implements People {

    private static final PeopleImpl INSTANCE;

    static {
        INSTANCE = new PeopleImpl();
    }

    public static PeopleImpl getInstance() {
        return INSTANCE;
    }

    private List<Person> people;

    private PeopleImpl() {
        people = JsonReader.getInstance().read();
    }

    @Override
    public List<Person> getPeople() {
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
                p -> p.getDateOfBirth().isBefore(LocalDate.now())).collect(Collectors.toList());
        TreeSet<LocalDate> birthDates = new TreeSet<>();
        for (Person p : contains) {
            birthDates.add(p.getDateOfBirth());
        }
        return birthDates;
    }

    public static Optional<Person> getPersonById(Integer integer) {
        List<Person> pers = PeopleImpl.getInstance().getPeople().stream().filter(p -> p.getPersonId() == integer).collect(Collectors.toList());
        Optional<Person> tmp = pers.stream().findFirst();
        return tmp;
    }

    public static List<PersonDto> filterBirthDates(LocalDate localDate) {
        List<Person> tmpList = PeopleImpl.getInstance().getPeople().stream().filter(p -> p.getDateOfBirth().isBefore(localDate)).collect(Collectors.toList());
        List<PersonDto> result = new ArrayList<>();
        for (Person p : tmpList) {
            PersonDto tmp = new PersonDto(p.getPersonId(), p.getFirstName() + " " + p.getLastName());
            result.add(tmp);
        }
        return result;
    }

    public static Optional<String> getPersonByIdAndBirthdate(Integer integer) {
        Optional<Person> person = getPersonById(integer);
        Person p = person.get();
        LocalDate bd = p.getDateOfBirth();
        String tmp = bd.getDayOfWeek().toString()
                + " " + bd.getDayOfMonth()
                + " " + bd.getMonth().toString()
                + " " + bd.getYear();
        List<String> tmpList = new ArrayList<>();
        tmpList.add(tmp);
        Optional<String> test = tmpList.stream().findFirst();
        return test;
    }

    public static OptionalDouble getAverageAge() {
        double averageAge = 0.0;
        int numPpl = 0;
        OptionalDouble test = PeopleImpl.getInstance().getPeople().stream().mapToDouble(Person::getAge).average();
        return test;
    }

    private static boolean isPalindrome(String s) {
        String str = s.toLowerCase();
        String rev = "";
        boolean ans = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);
        }

        if (str.equals(rev)) {
            ans = true;
        }
        return ans;

    }

    public static List<Person> getPalindromes() {
        ArrayList<Person> namePalindromes = new ArrayList<>();
        for (Person person : PeopleImpl.getInstance().getPeople()) {
            if (isPalindrome(person.getFirstName())) {
                namePalindromes.add(person);
            }
        }

        return namePalindromes;
    }

    /*public static Optional<Person> minAgeIs() {
        Optional<Person> tmp = PeopleImpl.getInstance().getPeople().stream().min((p1, p2) -> p1.getDateOfBirth().toEpochDay() < p2.getDateOfBirth().toEpochDay()).get();
        return tmp;
    }*/
}
