import scala.reflect.macros.Context
import scala.language.experimental.macros

/**
 * Created by DB Tsai on 8/13/10.
 */
object Tree {

  def getScorer(param: Double): Array[Double] => Double = macro scorer_impl

  def scorer_impl(c: Context)(param: c.Expr[Double]): c.Expr[Array[Double] => Double] = {
    import c.universe._
    val tree: c.universe.Tree = q"""if (input(0) > ${param}) 1.0 else 0.0"""

    c.Expr[Array[Double] => Double](
      q"""
         (input: Array[Double]) => {
           ${tree}
         }
       """
    )
  }
}
