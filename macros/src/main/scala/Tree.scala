import scala.reflect.macros.Context
import scala.language.experimental.macros

/**
 * Created by DB Tsai on 8/13/10.
 */
object Tree {

  def getScorer(): Array[Double] => Double = macro scorer_impl

  def scorer_impl(c: Context)(): c.Expr[Array[Double] => Double] = {
    import c.universe._
    val tree: c.universe.Tree = q"""if (input(0) > 2) 3.0 else 4.0"""

    c.Expr[Array[Double] => Double](
      q"""
         (input: Array[Double]) => {
           ${tree}
         }
       """
    )
  }
}
