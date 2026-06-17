package datastructure_algorithms.datastructure.nonlinear.tree.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class InfixToPostFix {

  private static final char LEFT_PARENTHESES = '(';
  private static final char RIGHT_PARENTHESES = ')';

  private boolean isOperator(char character) {
    return character == '^' || character == '/' || character == '*' || character == '+'
        || character == '-';
  }

  private int precedence(char character) {
    return switch (character) {
      case '^' -> 3;
      case '/', '*' -> 2;
      case '+', '-' -> 1;
      default -> -1;
    };
  }

  private String associativity(char character) {
    return character == '^' ? "RL" : "LR";
  }

  public String convertToPostfix(String infix) {
    StringBuilder result = new StringBuilder();
    Stack<Character> stack = new Stack();
    char[] characters = infix.toCharArray();
    for (char input : characters) {
      if (!isOperator(input) && input != LEFT_PARENTHESES
          && input != RIGHT_PARENTHESES) { // Operand
        result.append(input);
      } else if (input == LEFT_PARENTHESES) {
        stack.push(input);
      } else if (input == RIGHT_PARENTHESES) {
        while (!stack.isEmpty() && stack.peek() != LEFT_PARENTHESES) {
          result.append(stack.pop());
        }
        stack.pop();
      } else { // Operator
        while (!stack.isEmpty() && precedence(input) < precedence(stack.peek())) {
          result.append(stack.pop());
        }

        if (!stack.isEmpty() && precedence(input) > precedence(stack.peek())) {
          stack.push(input);
        } else if (!stack.isEmpty() && precedence(input) == precedence(stack.peek())) {
          String associativity = associativity(input);
          if (associativity.equals("RL")) {
            stack.push(input);
          } else {
            result.append(stack.pop());
            stack.push(input);
          }
        } else if (stack.isEmpty()) {
          stack.push(input);
        }
      }
    }
    while (!stack.isEmpty()) {
      result.append(stack.pop());
    }
    return result.toString();
  }

  public String evaluatePostFix(String postfix) {
    StringBuilder result = new StringBuilder();
    Stack<String> stack = new Stack<>();

    for (char input : postfix.toCharArray()) {
      if (isOperator(input)) {
        String pop2 = stack.pop();
        String pop1 = stack.pop();
        stack.push(new StringBuilder(pop1).append(input).append(pop2).toString());
      } else {
        stack.push(String.valueOf(input));
      }
    }
    while (!stack.isEmpty()) {
      result.append(stack.pop());
    }
    return result.toString();
  }


  public static void main(String[] args) {
    InfixToPostFix ex = new InfixToPostFix();
    // Convert to Postfix
    System.out.println(ex.convertToPostfix("A-B/C*D+E"));
    System.out.println(ex.convertToPostfix("K+L-M*N+(O^P)*W/U/V*T+Q"));
    System.out.println(ex.convertToPostfix("K+L-M*N+(O^P)*W/U/V*T+Q^J^A"));
    System.out.println(ex.convertToPostfix("A+(B*C-(D/E^F)*G)*H"));

    // Evaluation of Postfix
    System.out.println(ex.evaluatePostFix(ex.convertToPostfix("A-B/C*D+E")));
    System.out.println(ex.evaluatePostFix(ex.convertToPostfix("K+L-M*N+(O^P)*W/U/V*T+Q")));
    System.out.println(ex.evaluatePostFix(ex.convertToPostfix("K+L-M*N+(O^P)*W/U/V*T+Q^J^A")));
    System.out.println(ex.evaluatePostFix(ex.convertToPostfix("A+(B*C-(D/E^F)*G)*H")));
  }
}
