import scala.reflect.macros.Context
import scala.language.experimental.macros
import com.netflix.pvr.score.function.RegressionTreeScorer.Node

/**
 * Created by DB Tsai on 8/13/10.
 */
object Tree {

  def getScorer(root: Node): (String, Array[Double] => Double) = macro scorer_impl

  def scorer_impl(c: Context)(root: c.Expr[Node]): c.Expr[(String, Array[Double] => Double)] = {
    import c.universe._

//    val rootNode = reify(root.splice.getThreshold)

    val aa = show(root.tree)

    val tree: c.universe.Tree = q"""if (input(0) > 5.0) 1.0 else 0.0"""

    val code =
      q"""
         (input: Array[Double]) => {
         println($aa)
           ${tree}
         }
       """
    val textCode = showCode(code)
    c.Expr[(String, Array[Double] => Double)](q"($textCode, $code)")
  }
}
