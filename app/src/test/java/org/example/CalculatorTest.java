package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

  @Test
  void basicExamples() {
    Calculator c = new Calculator();
    assertEquals(7.0, c.evaluate("2 + 5"));
    assertEquals(33.0, c.evaluate("3 + 6 * 5"));
    assertEquals(20.0, c.evaluate("4 * (2 + 3)"));
    assertEquals(2.0, c.evaluate("(7 + 9) / 8"));
  }

  @Test
  void unaryMinusSupported() {
    Calculator c = new Calculator();
    assertEquals(7.0, c.evaluate("-3 + 5 * 2"));
    assertEquals(1.0, c.evaluate("-(2 - 3)")); // (2-3) = -1, unary minus -> 1
  }
}
