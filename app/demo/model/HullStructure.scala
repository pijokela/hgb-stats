package model

case class HullStructure(h: Int, s: Int)

object HullStructure {
  def apply(hs : String) : HullStructure = {
    val parts = hs.split(" */ *").map(Integer.parseInt(_))
    HullStructure(parts.head, parts.tail.head)
  }
}