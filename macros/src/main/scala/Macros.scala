import scala.reflect.macros.Context
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

object helloMacro {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    import Flag._
    val result: c.universe.Tree = {
      annottees.map(_.tree).toList match {
        case q"object $name extends ..$parents { ..$body }" :: Nil =>
          q"""
            object $name extends ..$parents {
              def hello: ${typeOf[String]} = "hello"
              ..$body
            }
          """
      }
    }
    c.Expr[Any](result)
  }
}

class hello extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro helloMacro.impl
}

object DebugMacros {

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
