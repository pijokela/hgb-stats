
import scala.util.Random

object Dice {
  val random = new Random
  
  def d6() : Int = Math.abs(random.nextInt()) % 6 + 1
  
  def d6withSkill(skill : Int)(dice : Int) : Int = {
    val rolls = for (i <- 1 to dice) yield d6()
    val sorted = rolls.sorted.reverse
    val (max, rest) = (sorted.head, sorted.tail)
    max + rest.filter(_ >= skill).length
  }
    
}