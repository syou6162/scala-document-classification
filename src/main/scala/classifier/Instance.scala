package classifier.instance

import scala.collection.immutable.HashMap

case class Instance(label: Int, featureVector: HashMap[Int, Double]) {
}
