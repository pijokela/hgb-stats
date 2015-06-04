import tsvparser._
import model._

object modParserTest {
	val headers = TsvParser.readHeaders("""#head	faction	chassis	name	tv	ua	mr	ar	hs	a	gu	pi	ew	weapons	traits	type	special""")
                                                  //> headers  : Vector[String] = Vector(#head, faction, chassis, name, tv, ua, mr
                                                  //| , ar, hs, a, gu, pi, ew, weapons, traits, type, special)
	val jagLine = """#line	NORTH	Jaguar	Jaguar	9	SK (0+), FS, RC	W:6" G:7"	6	3 / 3	1	3+	3+	5+	MAC (Arm, Split:2), LRP, APGL, LVB (Arm)	Agile, Arms	Gear 1.5""""
                                                  //> jagLine  : String = #line	NORTH	Jaguar	Jaguar	9	SK (0+), FS, RC	
                                                  //| W:6" G:7"	6	3 / 3	1	3+	3+	5+	MAC (Arm, Split:
                                                  //| 2), LRP, APGL, LVB (Arm)	Agile, Arms	Gear 1.5"
	val paraModLine = """#mod{chassis:'Jaguar'}	NORTH	Jaguar	#prefix:'Para'	+1	+PT	-	-	-	-	-	-	-	-	+Airdrop"""
                                                  //> paraModLine  : String = #mod{chassis:'Jaguar'}	NORTH	Jaguar	#prefix:
                                                  //| 'Para'	+1	+PT	-	-	-	-	-	-	
                                                  //| -	-	+Airdrop
  val jagMap = TsvParser.parseLine(jagLine, headers)
                                                  //> jagMap  : Map[String,String] = Map(name -> Jaguar, ar -> 6, pi -> 3+, weapon
                                                  //| s -> MAC (Arm, Split:2), LRP, APGL, LVB (Arm), ua -> SK (0+), FS, RC, ew -> 
                                                  //| 5+, hs -> 3 / 3, a -> 1, tv -> 9, chassis -> Jaguar, faction -> NORTH, gu ->
                                                  //|  3+, #head -> #line, mr -> W:6" G:7", type -> Gear 1.5", traits -> Agile, Ar
                                                  //| ms)
  val paraModMap = TsvParser.parseLine(paraModLine, headers)
                                                  //> paraModMap  : Map[String,String] = Map(name -> #prefix:'Para', ar -> -, pi -
                                                  //| > -, weapons -> -, ua -> +PT, ew -> -, hs -> -, a -> -, tv -> +1, chassis ->
                                                  //|  Jaguar, faction -> NORTH, gu -> -, #head -> #mod{chassis:'Jaguar'}, mr -> -
                                                  //| , traits -> +Airdrop)
                                                  
	val jag = Model(jagMap)                   //> jag  : model.Model = Model(Jaguar,6,9,List(Move(W,6), Move(G,7)),3,3,Some(5)
                                                  //| ,List(MAC (Arm, Split:2), LRP, APGL, LVB (Arm)),HullStructure(3,3),1,Jaguar,
                                                  //| NORTH)
                                                  
	val jagPara = Model.parseMods(paraModMap, jag :: Nil)
                                                  //> jagPara  : List[model.Model] = List(Model(Para Jaguar,6,9,List(Move(W,6), Mo
                                                  //| ve(G,7)),3,3,Some(5),List(MAC (Arm, Split:2), LRP, APGL, LVB (Arm)),HullStru
                                                  //| cture(3,3),1,Jaguar,NORTH))
                                                  
                                                  
                                                  
}