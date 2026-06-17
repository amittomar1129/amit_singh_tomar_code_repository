package datastructure_algorithms.datastructure.nonlinear.tree.expressions;

import java.util.Stack;

public class InfixToPrefix {

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

  public String convertToPrefix(String infix) {
    StringBuilder result = new StringBuilder();
    Stack<Character> stack = new Stack();

    String reverse = new StringBuilder(infix).reverse().toString();
    for (char input : reverse.toCharArray()) {
      if (!isOperator(input) && input != LEFT_PARENTHESES && input != RIGHT_PARENTHESES) {
        result.append(input);
      } else if (input == RIGHT_PARENTHESES) {
        stack.push(RIGHT_PARENTHESES);
      } else if (input == LEFT_PARENTHESES) {
        while (!stack.isEmpty() && stack.peek() != RIGHT_PARENTHESES) {
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
          if (associativity.equals("LR")) {
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
    return result.reverse().toString();
  }

  public String evaluatePreFix(String prefix) {
    StringBuilder result = new StringBuilder();
    Stack<String> stack = new Stack<>();
    String reverse = new StringBuilder(prefix).reverse().toString();
    for (char input : reverse.toCharArray()) {
      if (isOperator(input)) {
        String pop1 = stack.pop();
        String pop2 = stack.pop();
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
    InfixToPrefix ex = new InfixToPrefix();
    // Convert to Prefix
    System.out.println(ex.convertToPrefix("K+L-M*N+(O^P)*W/U/V*T+Q"));
    System.out.println(ex.convertToPrefix("K+L-M*N+(O^P)*W/U/V*T+Q^J^A"));
    System.out.println(ex.convertToPrefix("A+(B*C-(D/E^F)*G)*H"));

    // Evaluation of Prefix
    System.out.println(ex.evaluatePreFix(ex.convertToPrefix("K+L-M*N+(O^P)*W/U/V*T+Q")));
    System.out.println(ex.evaluatePreFix(ex.convertToPrefix("A+(B*C-(D/E^F)*G)*H")));
    System.out.println(ex.convertToPrefix("a*b/c+e/f*g+k-x*y"));





  }
}
