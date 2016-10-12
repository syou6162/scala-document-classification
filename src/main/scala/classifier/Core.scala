package classifier.core

import classifier.io.Parser
import classifier.perceptron.Perceptron
import classifier.evaluation.EvaluationMeasure
import scala.collection.immutable.HashMap

object Core {
  def main(args: Array[String]): Unit = {
    val train = Parser.getInstances("train.txt")
    val test = Parser.getInstances("test.txt")
    val goldLabels = test.map(_.label)

    val perceptron = Range(1, 10).foldLeft(new Perceptron(new HashMap[Int, Double], new HashMap[Int, Double], 0)) { (result1, iter) =>
      val perceptron = train.foldLeft(result1) { (result2, instance) =>
        result2.updateWeight(instance)
      }
      val averagedWeight = perceptron.getAveragedWeight()
      val predLabels = test.map(instance => perceptron.classify(averagedWeight, instance.featureVector))
      println(EvaluationMeasure.accuracy(goldLabels, predLabels))
      perceptron
    }
    val averagedWeight = perceptron.getAveragedWeight()
    val predLabels = test.map(instance => perceptron.classify(averagedWeight, instance.featureVector))
    println(EvaluationMeasure.accuracy(goldLabels, predLabels))
  }
}
