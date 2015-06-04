package tsvparser

import scala.io.Source
import java.io.File
import scala.io.Codec

object TsvParser {
  def readHeaders(file : File) : Vector[String] = {
    val source = Source.fromFile(file)(Codec.UTF8)
    val lines = source.getLines()
    readHeaders(lines)
  }
  
  def readHeaders(lines : Iterator[String]) : Vector[String] = {
    val headers = lines.next()
    readHeaders(headers)
  }
  
  def readHeaders(line : String) : Vector[String] = {
    line.split("\\t").toVector
  }
  
  def parseLine(line : String, headers : Vector[String]) : Map[String, String] = {
    val parts = line.split("\\t")
    parts.zipWithIndex.map(p=>headers(p._2) -> p._1).toMap
  }
  
  def read(fileName : String) : List[Map[String, String]] = {
    val file = new File(fileName)
    val source = Source.fromFile(file)(Codec.UTF8)
    val lines = source.getLines()
    val headers = readHeaders(lines)
    lines.map { line => parseLine(line, headers) }.toList
  }
}