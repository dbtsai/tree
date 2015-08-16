import scala.language.experimental.macros
import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox

/**
 * Created by DB Tsai on 8/13/10.
 */

trait Node

class Branch(index: Int, threshold: Double, left: Node, right: Node) extends Node {
  def getIndex(): Int = index

  def getThreshold(): Double = threshold

  def getLeft(): Node = left

  def getRight(): Node = right
}

class Leaf(value: Double) extends Node {
  def getValue(): Double = value
}

object Tree {
  val universe: scala.reflect.runtime.universe.type = scala.reflect.runtime.universe

  import universe._

  def NodeToTree(root: Node): universe.Tree = {
    root match {
      case node: Branch => q"if (input(${node.getIndex}) < ${node.getThreshold}) { ${NodeToTree(node.getLeft)} } else {  ${NodeToTree(node.getRight)} }"
      case node: Leaf => q"${node.getValue}"
    }
  }

  def getScorer(root: Node): Array[Double] => Double = {
    val toolbox = currentMirror.mkToolBox()
    val code =
      q"""
         (input: Array[Double]) => {
            ${NodeToTree(root)}
         }
       """
    print(showCode(code))
    toolbox.eval(code).asInstanceOf[Array[Double] => Double]
  }
}
