package org.specs.matcher

class mapMatchersSpec extends MatchersSpecification {
  "Map matchers" should { clearExample.before
    "provide an 'haveKey' matcher on maps: Map('one' -> 1, 'two' -> 2) must haveKey('one') [alias for not + haveKey = notHaveKey]" in {
      Map("one" -> 1, "two" -> 2) must haveKey("one")
      expectation(Map("one" -> 1, "two" -> 2) must haveKey("three")) must failWith("Map(one -> 1, two -> 2) doesn't have the key 'three'")
      expectation(Map("one" -> 1, "two" -> 2) aka "the map" must haveKey("three")) must failWith("the map Map(one -> 1, two -> 2) doesn't have the key 'three'")

      expectation(Map("one" -> 1, "two" -> 2) must notHaveKey("one")) must failWith("Map(one -> 1, two -> 2) has the key 'one'")
      expectation(Map("one" -> 1, "two" -> 2)aka "the map" must notHaveKey("one")) must failWith("the map Map(one -> 1, two -> 2) has the key 'one'")
    }
    "provide an 'haveValue' matcher on maps: Map('one' -> 1, 'two' -> 2) must haveValue(1) [alias for not + haveValue = notHaveValue]" in {
      Map("one" -> 1, "two" -> 2) must haveValue(1)
      expectation(Map("one" -> 1, "two" -> 2) must haveValue(3)) must failWith("Map(one -> 1, two -> 2) doesn't have the value '3'")
      expectation(Map("one" -> 1, "two" -> 2) aka "the map" must haveValue(3)) must failWith("the map Map(one -> 1, two -> 2) doesn't have the value '3'")

      expectation(Map("one" -> 1, "two" -> 2) must notHaveValue(1)) must failWith("Map(one -> 1, two -> 2) has the value '1'")
      expectation(Map("one" -> 1, "two" -> 2) aka "the map" must notHaveValue(1)) must failWith("the map Map(one -> 1, two -> 2) has the value '1'")
    }
    "provide an 'havePair' matcher on maps: Map('one' -> 1, 'two' -> 2) must havePair('one' -> 1) [alias for not + havePair = notHavePair]" in {
      Map("one" -> 1, "two" -> 2) must havePair("one" -> 1)
      expectation(Map("one" -> 1, "two" -> 2) must havePair("one" -> 3)) must failWith("Map(one -> 1, two -> 2) doesn't have the pair '(one,3)'")
      expectation(Map("one" -> 1, "two" -> 2) aka "the map" must havePair("one" -> 3)) must failWith("the map Map(one -> 1, two -> 2) doesn't have the pair '(one,3)'")

      expectation(Map("one" -> 1, "two" -> 2) must havePair("three" -> 1)) must failWith("Map(one -> 1, two -> 2) doesn't have the pair '(three,1)'")
      expectation(Map("one" -> 1, "two" -> 2) must havePair("three" -> 3)) must failWith("Map(one -> 1, two -> 2) doesn't have the pair '(three,3)'")
      expectation(Map("one" -> 1, "two" -> 2) must not(havePair("one" -> 1))) must failWith("Map(one -> 1, two -> 2) has the pair '(one,1)'")
      expectation(Map("one" -> 1, "two" -> 2) must notHavePair("one" -> 1)) must failWith("Map(one -> 1, two -> 2) has the pair '(one,1)'")
    }
    "provide an 'havePairs' matcher on maps: Map('one' -> 1, 'two' -> 2) must havePairs('one' -> 1, 'two' -> 2) [alias for not + havePairs = notHavePairs]" in {
      Map("one" -> 1, "two" -> 2) must havePairs("one" -> 1, "two" -> 2)
      expectation(Map("one" -> 1, "two" -> 2) must havePairs("one" -> 3)) must failWith("Map(one -> 1, two -> 2) doesn't have the pairs '(one,3)'")
    }
    val f = new PartialFunction[Int, String] {
        def isDefinedAt(i: Int) = i % 2 == 0
        def apply(i: Int) = (i*2).toString
    }
    "provide a beDefinedAt matcher checking if a PartialFunction is defined at specific values" in {
      f must beDefinedAt(2, 4, 6)
    }
    "provide a beDefinedBy matcher checking if a PartialFunction is defined at specific values and returns appropriate results" in {
      f must beDefinedBy(2 -> "4", 4 -> "8")
    }
    "provide a 'have the key' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must have the key("one")
    }
    "provide a 'have key' matcher on maps" in {
//      Map("one" -> 1, "two" -> 2) must have key("one")
    }
    "provide a 'not have key' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must not have key("three")
    }
    "provide a 'not have the key' matcher on maps" in {
 //     Map("one" -> 1, "two" -> 2) must not have the key("three")
    }
    "provide a 'have value' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must have value(1)
    }
    "provide a 'have the value' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must have the value(1)
    }
    "provide a 'not have value' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must not have value(3)
    }
    "provide a 'not have the value' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must not have the value(3)
    }
    "provide an 'have pair' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must have pair("one" -> 1)
    }
    "provide an 'not have pair' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must not have pair("three" -> 1)
    }
    "provide an 'have pairs' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must have pairs("one" -> 1, "two" -> 2)
    }
    "provide an 'not have pairs' matcher on maps" in {
      Map("one" -> 1, "two" -> 2) must not have pairs("three" -> 1, "two" -> 2)
    }
    "provide a be definedAt matcher checking if a PartialFunction is defined at specific values" in {
      f must be definedAt(2, 4, 6)
    }
    "provide a be definedBy matcher checking if a PartialFunction is defined at specific values and returns appropriate results" in {
      f must be definedBy(2 -> "4", 4 -> "8")
    }
  }
}
