import com.netflix.pvr.score.function.RegressionTreeScorer
import com.netflix.pvr.score.function.RegressionTreeScorer.{Leaf, Branch}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSuite

/**
 * Created by DB Tsai on 8/12/9.
 */

class TestSuite extends FunSuite with BeforeAndAfterAll {

  test("bytecode") {

    val left = new Branch(1, 3.0, new Leaf(4.0), new Leaf(-3.3))
    val right = new Branch(1, -5.0, new Leaf(-2.2), new Leaf(1.0))
    val root = new Branch(0, -5.0, left, right)
    val instance = new RegressionTreeScorer(root, false)


    val b = Tree.getScorer(5)

    val c = b(Array(10.0, 12.0, 11.0))

    val a = 10
  }
}
