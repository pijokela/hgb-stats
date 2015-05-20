package model

/**
 * name -> Model, 
 * ar -> AR, 
 * pi -> PI
 * weapons -> Weapons, 
 * ua -> UA, 
 * ew -> EW, 
 * hs -> H/S, 
 * a -> A, 
 * tv -> TV, 
 * chassis -> Chassis, 
 * faction -> Faction, 
 * gu -> GU
 */
case class Model(name: String, 
    ar: Int, 
    tv: Int, 
    mr: List[Move],
    gu: Int, 
    pi: Int, 
    ew: Int, 
    weapons: List[String], 
    hs : HullStructure, 
    a : Int, 
    chassis: String, 
    faction: String)

object Model {
  
  implicit def toInt(str : String) : Int = {
    if (str.endsWith("+"))
      Integer.parseInt(str.substring(0, 1))
    else
      Integer.parseInt(str)
  } 
  
  implicit def toStringList(str : String) : List[String] = {
    str.split(", *").toList
  } 
  
  def apply(statLine : Map[String, String]) : Model = {
    Model(
        name = statLine("name"),
        ar = statLine("ar"),
        tv = statLine("tv"),
        mr = Move(statLine("mr")),
        gu = statLine("gu"),
        pi = statLine("pi"),
        ew = statLine("ew"),
        weapons = statLine("weapons"),
        hs = HullStructure(statLine("hs")),
        a = statLine("a"),
        chassis = statLine("chassis"),
        faction = statLine("faction")
    )
  }
}

