package Episante.back.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleCalculatorTest {

    private SimpleCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new SimpleCalculator();
    }

    @Test
    @DisplayName("Should return 8 when adding 5 and 3")
    void testAdd_PositiveNumbers() {

        int numberA = 5;
        int numberB = 3;
        int expectedResult = 8;


        int actualResult = calculator.add(numberA, numberB);


        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Should return -2 when adding 5 and -7")
    void testAdd_PositiveAndNegative() {

        int numberA = 5;
        int numberB = -7;
        int expectedResult = -2;


        int actualResult = calculator.add(numberA, numberB);


        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Should return 0 when adding 0 and 0")
    void testAdd_Zeroes() {

        int numberA = 0;
        int numberB = 0;
        int expectedResult = 0;


        int actualResult = calculator.add(numberA, numberB);


        assertThat(actualResult).isEqualTo(expectedResult);
    }
}

