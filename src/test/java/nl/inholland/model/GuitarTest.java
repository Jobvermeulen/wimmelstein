package nl.inholland.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuitarTest {

    private Guitar guitar;
    @Before
    public void setup() {
        guitar = new Guitar("Fender", "Stratocaster", 1750);
    }
    @Test
    public void createGuitarShouldNotBeNull() {
        assertNotNull(guitar);
    }

    @Test
    public void newGuitarPriceShouldBeTheCorrectPrice() {
        assertEquals(1750, guitar.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativePriceShouldThrowException() {
        guitar.setPrice(-1);
    }

    @Test
    public void setPriceToOneShouldReturnOne() {
        guitar.setPrice(1);
        assertEquals(1, guitar.getPrice());
    }

    @Test
    public void numberOfStringsShouldBeSix() {
        assertEquals(6, guitar.getNumberOfStrings());
    }

    @Test
    public void isGuitarInstanceOfStringInstrument() {
        assertTrue(guitar instanceof StringInstrument);
    }

    @Test
    public void maxIntegerPlusOneIsZero() {
        assertEquals(0, Integer.MAX_VALUE+1);
    }
}