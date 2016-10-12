package classifier.evaluation

import scala.collection.immutable.Vector

object EvaluationMeasure {
  def accuracy(goldLabels: Vector[Int], predLabels: Vector[Int]): Double = {
    1.0 * goldLabels.zip(predLabels).count {case (x, y) => x == y} / goldLabels.size
  }
}
