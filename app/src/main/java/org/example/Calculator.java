package org.example;
import java.util.*;

public class Calculator {
  public double evaluate(String infix) {
    List<String> postfix = infixToPostfix(infix);
    return evalPostfix(postfix);
  }

  // (Shunting Yard)
  private List<String> infixToPostfix(String expr) {
    List<String> output = new ArrayList<>();
    Deque<String> ops = new ArrayDeque<>();

    List<String> tokens = tokenize(expr);

    for (String t : tokens) {
      if (isNumber(t)) {
        output.add(t);
      } else if (isOperator(t)) {
        while (!ops.isEmpty() && isOperator(ops.peek()) && precedence(ops.peek()) >= precedence(t)) {
          output.add(ops.pop());
        }
        ops.push(t);
      } else if (t.equals("(")) {
        ops.push(t);
      } else if (t.equals(")")) {
        while (!ops.isEmpty() && !ops.peek().equals("(")) {
          output.add(ops.pop());
        }
        if (!ops.isEmpty() && ops.peek().equals("(")) ops.pop();
      }
    }

    while (!ops.isEmpty()) {
      output.add(ops.pop());
    }
    return output;
  }

  private double evalPostfix(List<String> postfix) {
    Deque<Double> stack = new ArrayDeque<>();
    for (String t : postfix) {
      if (isNumber(t)) {
        stack.push(Double.parseDouble(t));
      } else if (isOperator(t)) {
        double b = stack.pop();
        double a = stack.pop();
        switch (t) {
          case "+": stack.push(a + b); break;
          case "-": stack.push(a - b); break;
          case "*": stack.push(a * b); break;
          case "/": stack.push(a / b); break;
        }
      }
    }
    return stack.pop();
  }

  private boolean isOperator(String t) {
    return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
  }

  private int precedence(String op) {
    if (op.equals("*") || op.equals("/")) return 2;
    if (op.equals("+") || op.equals("-")) return 1;
    return 0;
  }

  private boolean isNumber(String s) {
    if (s == null || s.isEmpty()) return false;
    char c0 = s.charAt(0);
    return (c0 == '-' && s.length() > 1) || Character.isDigit(c0);
  }

  private static int skipSpaces(String s, int i) {
    int n = s.length();
    while (i < n && Character.isWhitespace(s.charAt(i))) i++;
    return i;
  }

  private List<String> tokenize(String expr) {
    List<String> tokens = new ArrayList<>();
    int n = expr.length();
    int i = 0;
    String prev = null; 

    while (i < n) {
      i = skipSpaces(expr, i);
      if (i >= n) break;

      char ch = expr.charAt(i);

      if (ch == '(' || ch == ')') {
        tokens.add(String.valueOf(ch));
        prev = String.valueOf(ch);
        i++;
        continue;
      }

      if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        boolean unaryMinus = (ch == '-') && (prev == null || "(".equals(prev) || isOperator(prev));

        if (unaryMinus) {
          int j = skipSpaces(expr, i + 1);
          if (j < n && expr.charAt(j) == '(') {
            tokens.add("0");
            tokens.add("-");
            prev = "-";
            i++; // consume '-'; '(' handled in next loop
            continue;
          }

          StringBuilder sb = new StringBuilder();
          sb.append('-');
          i++;
          boolean hasDigits = false;
          while (i < n && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
            hasDigits = true;
            sb.append(expr.charAt(i));
            i++;
          }
          if (!hasDigits) {
            tokens.add("-");
            prev = "-";
            continue;
          }
          tokens.add(sb.toString());
          prev = sb.toString();
          continue;
        } else {
          tokens.add(String.valueOf(ch));
          prev = String.valueOf(ch);
          i++;
          continue;
        }
      }

      if (Character.isDigit(ch) || ch == '.') {
        StringBuilder sb = new StringBuilder();
        while (i < n && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
          sb.append(expr.charAt(i));
          i++;
        }
        tokens.add(sb.toString());
        prev = sb.toString();
        continue;
      }

      i++;
    }
    return tokens;
  }
}
