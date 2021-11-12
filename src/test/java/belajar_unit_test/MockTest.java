package belajar_unit_test;

import belajar_unit_test.data.Person;
import belajar_unit_test.repository.PersonRepository;
import belajar_unit_test.service.PersonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Extensions(
        @ExtendWith(MockitoExtension.class)
)
public class MockTest {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository);
    }

    @Test
    public void testMocking() {

        // membuat object dengan mocking
        List<String> list = Mockito.mock(List.class);

        Mockito.when(list.get(0)).thenReturn("andi");

        System.out.println(list.get(0));


    }

    @Test
    public void testGetPersonNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.get("not found");
        });
    }

    @Test
    public void testGetPersonSuccess() {
        Mockito.when(personRepository.selectById("andi"))
                .thenReturn(new Person("andiId", "andiName"));

        var person = personService.get("andi");

        Assertions.assertNotNull(person);
        Assertions.assertEquals("andiId", person.getId());
        Assertions.assertEquals("andiName", person.getName());
    }

    @Test
    public void testRegisterSuccess() {
        Person person = personService.register("andi");

        Assertions.assertNotNull(person);
        Assertions.assertEquals("andi", person.getName());
        Assertions.assertNotNull(person.getId());

        // verifikasi jika method insert dalam person repository sudah di panggil/digunakan sekali
        Mockito.verify(personRepository, Mockito.times(1))
                .insert(new Person(person.getId(), person.getName()));
    }

    @Test
    public void testLazyStream() {

        Stream<String> names = Stream.of("andi", "saputro");

        names.map(name -> {
            System.out.println("Stream jalan " + name);
            return name.toUpperCase();
        });
    }

    @Test
    public void testTerminalStream() {

        Stream<String> names = Stream.of("andi", "saputro");

        names.map(name -> {
            System.out.println("Stream jalan " + name);
            return name.toUpperCase();
        }).forEach(name -> {
            System.out.println("print nama: " + name);
        });
    }

    @Test
    public void testSortedComparator() {
        Stream<String> data = Stream.of("rahma", "andi", "wulan", "sarah");

        data.sorted(Comparator.comparing(value -> value))
                .forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        Stream<String> data = Stream.of("joko", "andi", "oki", "andi", "joko", "oki", "oki", "sarah", "andi", "oki", "sarah", "oki", "sarah");

//        melihat data tanpa merubah tipe datanya dengan lazy operation peek
//        data.peek(System.out::println);

        // grouping by name
        Map<String, Long> count = data.collect(Collectors.groupingBy(key -> key, Collectors.counting()));

        System.out.println(count);


    }

}
