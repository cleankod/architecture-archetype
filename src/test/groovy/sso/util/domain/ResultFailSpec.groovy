package sso.util.domain

import spock.lang.Specification

class ResultFailSpec extends Specification {
  def "should create fail result without value"() {
    when:
    def result = Result.fail()

    then:
    result.isFail()
    !result.isSuccessful()
  }

  def "should return given value"() {
    given:
    def value = "value"
    def result = Result.fail(value)

    when:
    def resultValue = result.failValue()

    then:
    resultValue == value
  }

  def "should throw exception when access to void value"() {
    given:
    Result<Void, Void> result = Result.fail()

    when:
    result.failValue()

    then:
    thrown(NullPointerException)
  }

  def "should throw exception when result value is null"() {
    when:
    Result.fail(null)

    then:
    thrown(NullPointerException)
  }
}
