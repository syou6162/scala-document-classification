package classifier.perceptron

import scala.collection.immutable.HashMap
import classifier.instance.Instance

class Perceptron(val weight: HashMap[Int, Double], val cumWeight: HashMap[Int, Double], val count: Int) {
  type Weight = HashMap[Int, Double]
  type FeatureVector = HashMap[Int, Double]
  def getScore(weight: Weight, featureVector: FeatureVector): Double = {
    featureVector.foldLeft(0.0) { (result, kv) =>
      result + weight.getOrElse(kv._1, 0.0) * kv._2
    }
  }
  def getScore(featureVector: FeatureVector): Double = {
    getScore(weight, featureVector)
  }
  def updateWeight(instance: Instance): Perceptron = {
    val predLabel = classify(instance.featureVector)
    if (instance.label != predLabel) {
      val newWeight = instance.featureVector.foldLeft(weight) { (result, kv) =>
        result.updated(kv._1, result.getOrElse(kv._1, 0.0) + instance.label * kv._2)
      }
      val newCumWeight = instance.featureVector.foldLeft(cumWeight) { (result, kv) =>
        result.updated(kv._1, result.getOrElse(kv._1, 0.0) + count * instance.label * kv._2)
      }
      new Perceptron(newWeight, newCumWeight, count + 1)
    } else {
      this
    }
  }
  def getAveragedWeight(): Weight = {
    cumWeight.foldLeft(weight) { (result, kv) =>
      result.updated(kv._1, weight.getOrElse(kv._1, 0.0) - kv._2 / count)
    }
  }
  def classify(weight: Weight, featureVector: FeatureVector): Int = {
    if (getScore(weight, featureVector) >= 0) 1 else -1
  }
  def classify(featureVector: FeatureVector): Int = {
    classify(weight, featureVector)
  }
}
