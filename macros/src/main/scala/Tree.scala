import scala.reflect.macros.Context
import scala.language.experimental.macros
import com.netflix.pvr.score.function.RegressionTreeScorer.{Leaf, Branch, Node}

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

  def NodeToCode(root: Node): String = {
    root match {
      case node: Branch => s"if (input(${node.getIndex}) < ${node.getThreshold}) { ${NodeToCode(node.getLeft)} } else {${NodeToCode(node.getRight)}}"
      case node: Leaf => s"${node.getValue}"
    }
  }

  def getScorer(root: Node): (String, Array[Double] => Double) = macro scorer_impl

  def scorer_impl(c: Context)(root: c.Expr[Node]) = {
    import c.universe._

    val tree = q"""if (input(0) < -5.0) { if (input(1) < 3.0) { 4.0 } else {-3.3} } else {if (input(1) < -5.0) { -2.2 } else {1.0}}"""

    val code =
      q"""
         (input: Array[Double]) => {
            $tree
         }
       """

    val scalaCode = showCode(code)
    q"($scalaCode, $code)"
  }
}
