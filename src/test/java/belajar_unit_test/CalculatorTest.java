package belajar_unit_test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Test Calculator")
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test skenario sukses calculator.add(Integer, Integer)")
    public void testAddSuccess(){

        var result = calculator.add(10, 10);

        assertEquals(20, result);

    }

    @Test
    @DisplayName("Test skenario sukses calculator.divide(Integer, Integer)")
    public void testDivideSuccess(){

        var result = calculator.divide(100, 10);

        assertEquals(10, result);

    }

    @Test
    @Disabled
    @DisplayName("Test skenario gagal calculator.divide(Integer, Integer)")
    public void testDivideFailed(){
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(100, 0);
        });
    }

    @Test
    public void testAborted(){
        var profile = System.getenv("PROFILE");

//        if(!"DEV".equals(profile)) throw new TestAbortedException("Test dibatalkan karena enviroment bukan DEV");
        assumeTrue("DEV".equals(profile), "Test dibatalkan karena enviroment bukan DEV");
    }

}
