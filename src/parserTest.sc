import tsvparser.TsvParser._
import model._

object parserTest {
  new java.io.File(".").getAbsolutePath           //> res0: String = /home/pirkka/eclipse/scala-SDK-4.0.0-vfinal-2.11-linux/.
  val fileName = "/home/pirkka/git/hgb-stats/data/Statlines.tsv.txt"
                                                  //> fileName  : String = /home/pirkka/git/hgb-stats/data/Statlines.tsv.txt
	readHeaders(fileName)                     //> res1: Vector[String] = Vector(#head, faction, chassis, name, tv, ua, mr, ar,
                                                  //|  hs, a, gu, pi, ew, weapons, traits, type, special)
	
	val tsv = read(fileName)                  //> tsv  : List[Map[String,String]] = List(Map(name -> Model, ar -> AR, pi -> PI
                                                  //| , weapons -> Weapons, ua -> UA, ew -> EW, hs -> H/S, a -> A, tv -> TV, chass
                                                  //| is -> Chassis, faction -> Faction, gu -> GU, #head -> #labels, mr -> MR, spe
                                                  //| cial -> Special Rules, type -> Type/Height, traits -> Traits), Map(name -> s
                                                  //| tr, ar -> i, pi -> str, weapons -> array(str,','), ua -> array(str,','), ew 
                                                  //| -> array(str,','), hs -> array(i,'/'), a -> str, tv -> i, chassis -> str, fa
                                                  //| ction -> str, gu -> str, #head -> #types, mr -> array(str' '), traits -> str
                                                  //| ), Map(name -> Hunter, ar -> 6, pi -> 4+, weapons -> LAC (Arm, Split:2), LRP
                                                  //| , APGL, LPZ, LVB (Arm), ua -> GP(0+), FS, ST, HT, ew -> 6+, hs -> 4 / 2, a -
                                                  //| > 1, tv -> 6, chassis -> Hunter, faction -> NORTH, gu -> 4+, #head -> #line,
                                                  //|  mr -> W:5" G:6", type -> Gear 1.5", traits -> Arms), Map(name -> Hunter Gun
                                                  //| ner, ar -> 6, pi -> 4+, weapons -> MAC (Arm), LRP, APGL, LVB (Arm), ua -> GP
                                                  //| , SK , FS, ew -> 6+, hs 
                                                  //| Output exceeds cutoff limit.
	tsv.head                                  //> res2: Map[String,String] = Map(name -> Model, ar -> AR, pi -> PI, weapons ->
                                                  //|  Weapons, ua -> UA, ew -> EW, hs -> H/S, a -> A, tv -> TV, chassis -> Chassi
                                                  //| s, faction -> Faction, gu -> GU, #head -> #labels, mr -> MR, special -> Spec
                                                  //| ial Rules, type -> Type/Height, traits -> Traits)
	
	val modelLine = tsv.tail.tail.head        //> modelLine  : Map[String,String] = Map(name -> Hunter, ar -> 6, pi -> 4+, wea
                                                  //| pons -> LAC (Arm, Split:2), LRP, APGL, LPZ, LVB (Arm), ua -> GP(0+), FS, ST,
                                                  //|  HT, ew -> 6+, hs -> 4 / 2, a -> 1, tv -> 6, chassis -> Hunter, faction -> N
                                                  //| ORTH, gu -> 4+, #head -> #line, mr -> W:5" G:6", type -> Gear 1.5", traits -
                                                  //| > Arms)

	val model = Model(modelLine)              //> model  : model.Model = Model(Hunter,6,6,List(Move(W,5), Move(G,6)),4,4,6,Lis
                                                  //| t(LAC (Arm, Split:2), LRP, APGL, LPZ, LVB (Arm)),HullStructure(4,2),1,Hunter
                                                  //| ,NORTH)
                                              
	
}