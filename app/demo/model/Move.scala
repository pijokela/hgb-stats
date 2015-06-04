package model

case class Move(mode : String, distance : Int)

object Move {
  def apply(moveString : String) : List[Move] = {
    val parts = moveString.split(" ").map(p=>(p.substring(0, 1), p.substring(2, p.length()-1)))
    parts.toList.map { case(a,b) => Move(a,Integer.parseInt(b)) }
  }
}