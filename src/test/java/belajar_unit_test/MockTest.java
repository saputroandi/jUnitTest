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

import java.util.List;

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
    public void testMocking(){

        // membuat object dengan mocking
        List<String> list = Mockito.mock(List.class);

        Mockito.when(list.get(0)).thenReturn("andi");

        System.out.println(list.get(0));


    }

    @Test
    public void testGetPersonNotFound(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.get("not found");
        });
    }

    @Test
    public void testGetPersonSuccess(){
        Mockito.when(personRepository.selectById("andi"))
                .thenReturn(new Person("andiId", "andiName"));

        var person = personService.get("andi");

        Assertions.assertNotNull(person);
        Assertions.assertEquals("andiId", person.getId());
        Assertions.assertEquals("andiName", person.getName());
    }

    @Test
    public void testRegisterSuccess(){
        var person = personService.register("andi");

        Assertions.assertNotNull(person);
        Assertions.assertEquals("andi", person.getName());
        Assertions.assertNotNull(person.getId());

        // verifikasi jika method insert dalam person repository sudah di panggil/digunakan sekali
        Mockito.verify(personRepository, Mockito.times(1))
                .insert(new Person(person.getId(), person.getName()));
    }

}
