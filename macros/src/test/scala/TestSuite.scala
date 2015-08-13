import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSuite

/**
 * Created by DB Tsai on 8/12/9.
 */

class TestSuite extends FunSuite with BeforeAndAfterAll {

  test("bytecode") {
   val b = Tree.getScorer()

    val c = b(Array(10.0, 12.0, 11.0))

    val a = 10
  }
}
