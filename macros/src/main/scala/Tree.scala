import scala.reflect.macros.Context
import scala.language.experimental.macros

/**
 * Created by DB Tsai on 8/13/10.
 */
object Tree {

  def getScorer(threshold: Double): (String, Array[Double] => Double) = ("ddd", (input: Array[Double]) => 3.0)//macro scorer_impl

  def scorer_impl(c: Context)(threshold: c.Expr[Double]): c.Expr[(String, Array[Double] => Double)] = {
    import c.universe._
    val tree: c.universe.Tree = q"""if (input(0) > threshold.value) 3.0 else 4.0"""

    val code =
      q"""
         (input: Array[Double]) => {
           ${tree}
         }
       """
    val textCode = showCode(code)
    c.Expr[(String, Array[Double] => Double)](q"($textCode, $code)")
  }
}
