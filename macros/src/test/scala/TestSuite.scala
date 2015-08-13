import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSuite

/**
 * Created by DB Tsai on 8/12/9.
 */

class TestSuite extends FunSuite with BeforeAndAfterAll {

  test("bytecode") {
    //    val b = Tree.getScorer{val a = 5.0}

    val codeGen = Tree.getScorer(10)
    //    print(b._1)
    //    val fun = b._2
    //    val c = fun(Array(10.0, 12.0, 11.0))

    println("\ncool")
    val a = 10
  }
}
