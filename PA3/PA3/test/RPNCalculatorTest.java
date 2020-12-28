import org.junit.Test;

import java.util.EmptyStackException;
import static org.junit.Assert.*;

public class RPNCalculatorTest {
    RPNCalculator calc = new RPNCalculator();

    @Test
    public void testAdd() throws CalculatorException {
        calc.processLine("2 5 +");
        assertEquals(7.0,calc.pop(), 0.0001);
    }

    @Test
    public void testSub() throws CalculatorException {
        calc.processLine("6 4 -");
        assertEquals(2.0,calc.pop(), 0.0001);
    }

    @Test
    public void testMul() throws CalculatorException {
        calc.processLine("3 4 *");
        assertEquals(12.0,calc.pop(), 0.0001);
    }

    @Test
    public void testDiv() throws CalculatorException {
        calc.processLine("8 2 /");
        assertEquals(4.0,calc.pop(), 0.0001);
    }

    @Test
    public void testDiv1() throws CalculatorException {
        calc.processLine("9 2 /");
        assertEquals(4.5,calc.pop(), 0.0001);
    }

    @Test
    public void testPow() throws CalculatorException {
        calc.processLine("4 3 ^");
        assertEquals(64,calc.pop(), 0.0001);
    }

    @Test
    public void testLog2() throws CalculatorException {
        calc.processLine("8 log");
        assertEquals(2.0794,calc.pop(), 0.0001);
    }

    @Test
    public void testCos() throws CalculatorException {
        calc.processLine("0 cos");
        assertEquals(1,calc.pop(), 0.0001);
    }

    @Test
    public void testSin() throws CalculatorException {
        calc.processLine("1 sin");
        assertEquals(0.841,calc.pop(), 0.001);
    }

    @Test
    public void testExp() throws CalculatorException {
        calc.processLine("4 exp");
        assertEquals(54.598,calc.pop(), 0.0002);
    }
    @Test
    public void testSqrt() throws CalculatorException {
        calc.processLine("5 sqrt");
        assertEquals(2.236,calc.pop(), 0.0002);
    }

    @Test
    public void testLogLong() throws CalculatorException {
        calc.processLine("8 log 2 log /");
        assertEquals(3,calc.pop(), 0.0001);
    }

    @Test
    public void testLongStr() throws CalculatorException {
        calc.processLine("2 3 4 + * 0.5 /");
        assertEquals(28,calc.pop(), 0.0001);
    }
    @Test
    public void testLongStr1() throws CalculatorException {
        calc.processLine("3 2 ^ 4 2 ^ + sqrt");
        assertEquals(5,calc.pop(), 0.0001);
    }

    @Test
    public void testLongStr2() throws CalculatorException {
        calc.processLine("3 2 ^ 4 2 ^ + 5 3 * 6 4 + + sqrt +");
        assertEquals(30, calc.pop(), 0.0001);
    }

    @Test(expected = EmptyStackException.class)
    public void testEmpty() throws CalculatorException {
        calc.processLine("");
        System.out.println(calc.pop());
    }

    @Test(expected = CalculatorException.class)
    public void testUnknown() throws CalculatorException {
        calc.processLine("2 3 unknown");
        System.out.println(calc.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void testClear() {
        calc.enter(1.0);
        calc.enter(2.0);
        calc.enter(3.0);
        calc.clear();
        System.out.println(calc.pop());
    }
}
