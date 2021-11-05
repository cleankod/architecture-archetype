package sso.util.string;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BytesToHexConverter {
  public static String convert(byte[] bytes) {
    if (bytes == null) {
      return "";
    }
    StringBuilder hexString = new StringBuilder(2 * bytes.length);
    for (final byte aByte : bytes) {
      String hex = Integer.toHexString(0xff & aByte);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
