object Messages {
  val message = "hello, world"

  // Dummy implicit to show how they are annotated by sxr
  implicit def intToMessage(i: Int) = new IntMessage(i)
}

class IntMessage(val i: Int) {
  def asMessage = "hello from " + i
}
