import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Hello" should "have tests" in {
    true should === (true)
  }

  it should "say true" in {
    true should === (true)
  }
}
