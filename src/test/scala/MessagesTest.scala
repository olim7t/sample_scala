import org.scalatest.FunSuite

class MessagesTest extends FunSuite {
  test("Integers are converted with an implicit") {
    import Messages._
    assert(42.asMessage === "hello from 42")
  }
}
