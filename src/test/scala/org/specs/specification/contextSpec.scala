package org.specs.specification
import org.specs._

object contextSpec extends LiterateSpecification with ContextDefinitions with Wiki { 

"Contexts" ->> <wiki>

There are 2 types of contexts that can be set on a System under specification, to provide a way to manage the data that examples are using:
* shared contexts: {linkTo("Shared contexts")}  
* system contexts: {linkTo("System contexts")}  

Shared contexts allow to defined @before@ and @after@ operations that will set up and clear the specification variables used by the examples so that each example will operate on a known state.
  
On the other hand system contexts provide a way to specify a @SystemContext@ object which will be passed to each example for its sole usage. This way of using contexts is a bit more verbose than defining shared contexts but it has 2 advantages over shared contexts: 
* the set of data the example is operating on is clearly defined as a "System"
* examples can be executed in parallel because they don't share the same variables
 
{includeSus("Shared contexts")}
{includeSus("System contexts")}

</wiki>

"Shared contexts".definedAs(shared) ->> <wiki>

h4. Introduction

Shared contexts are defined using the before/after methods on the Context class, to set or reset those variables before and after execution of the examples. They usually share variables defined on the specification object.  

h4. Examples

<ex>A sus with a shared context should use its before method to set shared variables before executing an example</ex>{ 
  exampleOk(0) }
<ex>If two examples refer to the same variable, modifications made by the first example can be seen by the second one</ex>{
  exampleOk(1) }
</wiki>    

"System contexts".definedAs(systemContexts) ->> <wiki>

System contexts are defined by subclassing the @SystemContext@ class and by defining the @newInstance@ method which will provide a fresh instance of the system, in a specific context.
The construction of the system and its initialization can be separated by doing the initialization of the system in the @newInstance@ method while overriding the @before@ method to set up specific variables on the system. This helps in defining a hierarchy of contexts differing only by their @before@ methods.

h4. Examples

<ex>A sus with a system context should pass a system instance to the examples</ex>{ exampleOk(0) }
<ex>Each example should get a fresh copy of the system in its specific context</ex>{ exampleOk(1) }
</wiki>

  def exampleIsOk(e: Example) = e.isOk aka e.description.toString must beTrue
  def exampleOk(i: Int) = forExample { (s: Specification) => 
    s.examples.map(_.failures)
    exampleIsOk(s.examples(i)) 
  }
}
trait ContextDefinitions {
  case class SpecificationWithSharedContext extends Specification {
    var sharedCounter = 0
    val sharedContext = beforeContext { sharedCounter = sharedCounter + 1 }
    "A sus with a" ->- sharedContext should {
      "increment a shared variable before the first example" in { sharedCounter must_== 1 }
      "increment a shared variable before the second example" in { sharedCounter must_== 2 }
    }
  } 
  def shared = new SystemContext[SpecificationWithSharedContext] {
    def newSystem = SpecificationWithSharedContext()
  }
  case class SpecificationWithSystemContext extends Specification {
    var system1: System = _
    case class System { 
      var counter = 0
    }
    def initializedWithASystem = new SystemContext[System] {
      def newSystem = new System()
      override def before(s: System) = s.counter = s.counter + 1
    }
    "the system has be passed to the example and initialized" in { system: System =>
      system1 = system
      system.counter must_== 1
    }
    "the passed system was a fresh copy" in { system: System =>
      system1 mustNotEq system
      system.counter must_== 1
    }
  }
  def systemContexts = new SystemContext[SpecificationWithSystemContext] {
    def newSystem = SpecificationWithSystemContext()
  }
}
import org.specs.runner._
class contextSpecTest extends JUnit4(contextSpec) with Html {
  override def outputDir = normalize("target")
}
