import Dice._

object onevsone {
	
	d6                                        //> res0: Int = 5
	
	val jager = d6withSkill(4) _              //> jager  : Int => Int = <function1>
	val bm = d6withSkill(3) _                 //> bm  : Int => Int = <function1>
	val bmLaser = d6withSkill(2) _            //> bmLaser  : Int => Int = <function1>
	
	val r = (1 to 100000).toList              //> r  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                                                  //|  17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
                                                  //|  36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54,
                                                  //|  55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73,
                                                  //|  74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92,
                                                  //|  93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 10
                                                  //| 9, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124
                                                  //| , 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139,
                                                  //|  140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 
                                                  //| 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 1
                                                  //| 70, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 18
                                                  //| 5, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200
                                                  //| , 201, 202, 203, 204, 20
                                                  //| Output exceeds cutoff limit.
	def average(fn: => Int) : Double = {
		(r.map(i=>fn).sum).toDouble / r.length.toDouble
	}                                         //> average: (fn: => Int)Double
	
	def percentGroups(fn: => Int) : List[(Int, Double)] = {
		val divisor = r.length.toDouble / 100.0
		r.map(i=>fn).groupBy(i=>i).map(pair=>(pair._1,pair._2.length / divisor)).toList.sorted
	}                                         //> percentGroups: (fn: => Int)List[(Int, Double)]
	
	// Averages Jager shooting:
	average(jager(1))                         //> res1: Double = 3.50194
	average(jager(2))                         //> res2: Double = 4.71774
	average(jager(3))                         //> res3: Double = 5.58739
	average(jager(4))                         //> res4: Double = 6.31742
	
  // Averages BM shooting:
	average(bm(1))                            //> res5: Double = 3.50421
	average(bm(2))                            //> res6: Double = 4.91191
	average(bm(3))                            //> res7: Double = 5.99567
	average(bm(4))                            //> res8: Double = 6.92453
	
	// Averages BM shooting LASER:
	average(bmLaser(1))                       //> res9: Double = 3.50122
	average(bmLaser(2))                       //> res10: Double = 5.16442
	average(bmLaser(3))                       //> res11: Double = 6.45056
	average(bmLaser(4))                       //> res12: Double = 7.57412
	
	percentGroups(jager(1))                   //> res13: List[(Int, Double)] = List((1,16.803), (2,16.634), (3,16.375), (4,16.
                                                  //| 918), (5,16.703), (6,16.567))
	percentGroups(jager(2))                   //> res14: List[(Int, Double)] = List((1,2.799), (2,8.184), (3,13.958), (4,16.67
                                                  //| 9), (5,19.527), (6,24.759), (7,14.094))
	percentGroups(jager(3))                   //> res15: List[(Int, Double)] = List((1,0.462), (2,3.303), (3,8.789), (4,12.329
                                                  //| ), (5,16.716), (6,25.576), (7,24.17), (8,8.655))
	percentGroups(jager(4))                   //> res16: List[(Int, Double)] = List((1,0.082), (2,1.172), (3,5.08), (4,8.351),
                                                  //|  (5,12.521), (6,21.698), (7,27.229), (8,18.752), (9,5.115))
	
}