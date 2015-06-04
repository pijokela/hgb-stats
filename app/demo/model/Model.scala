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
    ew: Option[Int], 
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
  
  implicit def toOptionInt(str : String) : Option[Int] = 
    if (str == "-")
      None
    else
      Some(toInt(str))
  
  implicit def toStringList(str : String) : List[String] = {
    str.split(", *").toList
  }
  
  /**
   * True if the line is a data line that contains a new model.
   * False if the line is a mod.
   */
  private def isDataLine(line : Map[String, String]) : Boolean = 
    try {
      line("#head") == "#line"
    } catch { 
      case e : Exception => throw new ModelParserException(s"no #head on line: ${line}", e) 
    }    
  
  
  def apply(statLines : List[Map[String, String]]) : List[Model] = {
    val (lines, mods) = statLines.filter(!_.isEmpty).partition(isDataLine(_))
    val lineModels = lines.flatMap(line => try { 
      Some(Model(line)) 
    } catch {case e : Exception => None})
    
    val modModels = mods.flatMap(mod => try { 
      Some(Model.parseMods(mod, lineModels)) 
    } catch {case e : Exception => None}).flatten
    
    lineModels ++ modModels
  }
  
  def apply(statLine : Map[String, String]) : Model = {
    try {
      Model(
          name    = statLine("name"),
          ar      = statLine("ar"),
          tv      = statLine("tv"),
          mr      = Move(statLine("mr")),
          gu      = statLine("gu"),
          pi      = statLine("pi"),
          ew      = statLine("ew"),
          weapons = statLine("weapons"),
          hs      = HullStructure(statLine("hs")),
          a       = statLine("a"),
          chassis = statLine("chassis"),
          faction = statLine("faction")
      )
    } 
    catch {
    case e : Exception => throw new ModelParserException(s"stats line: ${statLine}", e)
    }
  }
  
  /**
   * #mod{chassis:'Tiger'}
   */
  def parseModTargetChassis(head : String) : String = {
    val Groups = "#mod\\{chassis:'(.+)'\\}".r
    head match {
      case Groups(chassis) => chassis
      case _ => throw new ModelParserException(s"Could not match head: $head to regexp. Chassis not found.")
    }
  }
  
  def parseMods(mod : Map[String, String], lineModels : List[Model]) : List[Model] = {
    lineModels.filter(_.chassis == parseModTargetChassis(mod("#head"))).map(parseMod(mod, _))
  }
  
  def parseModName(modNameField : String, lineModelName : String) : String = {
    val Prefix = "#prefix:'(.+)'".r
    // #replace:{from:'Tiger', to:'Sabertooth'}
    val Replace = "#replace:\\{from:'(.+)', to:'(.+)'}".r
    modNameField match {
      case Prefix(namePrefix) => namePrefix + " " + lineModelName
      case Replace(from, to) => lineModelName.replace(from, to)
      case _ => lineModelName
    }
  }
  
  def parseMod(mod : Map[String, String], lineModel : Model) : Model = {
    lineModel.copy(
      name = parseModName(mod("name"), lineModel.name)
    )
  }
}

class ModelParserException(msg : String, e : Exception = null) extends Exception(msg, e)

