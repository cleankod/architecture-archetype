package sso.util.string

import spock.lang.Specification

class BytesToHexConverterSpec extends Specification {

  def "should convert bytes value to hex string"() {
    when:
    def actualResult = BytesToHexConverter.convert(value)

    then:
    actualResult == expectedResult

    where:
    value           || expectedResult
    "one".bytes     || "6f6e65"
    "one two".bytes || "6f6e652074776f"
    " ".bytes       || "20"
    "".bytes        || ""
    null            || ""
  }
}
