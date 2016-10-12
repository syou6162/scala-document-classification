package classifier.io

import scala.io.Source
import classifier.instance.Instance
import scala.collection.immutable.HashMap
import scala.collection.immutable.Vector

object Parser {
  def parse(line: String): Instance = {
    val ary = line.split(" ")
    val label = ary(0).toInt
    val featureVector = ary.drop((1)).foldLeft(new HashMap[Int, Double]){ (result, chunk) =>
      val tmp = chunk.split(":")
      result.updated(tmp(0).toInt, tmp(1).toDouble)
    }
    new Instance(label, featureVector)
  }
  def getInstances(fileName: String): Vector[Instance] = {
    val source = Source.fromFile(fileName)
    val lines = source.getLines.toVector
    lines.map(parse)
  }
}
