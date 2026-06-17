package datastructure_algorithms.patterns.strings.regex;

//  Write a function to check whether an input string is a valid IPv4 address or IPv6 address or
// neither.
//
//  Example 1:
//  Input: input = "172.16.254.1"
//  Output: "IPv4"
//  Explanation: This is a valid IPv4 address.
//
//  Example 2:
//  Input: input = "2001:0db8:85a3:0:0:8A2E:0370:7334"
//  Output: "IPv6"
//  Explanation: This is a valid IPv6 address.
//
//  Example 3:
//  Input: input = "256.256.256.256"
//  Output: "Neither"
//  Explanation: This is neither a valid IPv4 address nor a valid IPv6 address.

//  An IPv4 address:
//  Has 4 parts separated by .
//  Each part:
//  Is numeric only
//  Value is between 0 and 255
//  No leading zeros unless the number is exactly "0"

//  An IPv6 address:
//  Has 8 parts separated by :
//  Each part:
//  Length between 1 and 4
//  Contains only hex characters -> 0-9, a-f, A-F

//  Time -> O(n)
//  Space -> O(1)

public class ValidateIPAddress {

  public static String validIPAddress(String IP) {
    if (IP.indexOf('.') >= 0) {
      return isIPv4(IP) ? "IPv4" : "Neither";
    } else if (IP.indexOf(':') >= 0) {
      return isIPv6(IP) ? "IPv6" : "Neither";
    } else {
      return "Neither";
    }
  }

  private static boolean isIPv4(String ip) {
    String[] parts = ip.split("\\.", -1);

    if (parts.length != 4) return false;

    for (String part : parts) {
      if (part.length() == 0 || part.length() > 3) return false;

      // no leading zero
      if (part.length() > 1 && part.charAt(0) == '0') return false;

      for (char c : part.toCharArray()) {
        if (!Character.isDigit(c)) return false;
      }

      int val = Integer.parseInt(part);
      if (val < 0 || val > 255) return false;
    }
    return true;
  }

  private static boolean isIPv6(String ip) {
    String[] parts = ip.split(":", -1);

    if (parts.length != 8) return false;

    for (String part : parts) {
      if (part.length() == 0 || part.length() > 4) return false;

      for (char c : part.toCharArray()) {
        if (!isHexChar(c)) return false;
      }
    }
    return true;
  }

  private static boolean isHexChar(char c) {
    return Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
  }

  public static void main(String[] args) {
    System.out.println(validIPAddress("172.16.254.1"));
    System.out.println(validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    System.out.println(validIPAddress("256.256.256.256"));
  }
}
