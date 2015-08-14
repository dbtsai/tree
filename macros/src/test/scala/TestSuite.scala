import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSuite

/**
 * Created by DB Tsai on 8/12/9.
 */


class TestSuite extends FunSuite with BeforeAndAfterAll {

  test("bytecode") {

    val left = new Branch(1, 3.0, new Leaf(4.0), new Leaf(-3.3))
    val right = new Branch(1, -5.0, new Leaf(-2.2), new Leaf(1.0))
    val root: Branch = new Branch(0, -5.0, left, right)

    println(Tree.NodeToCode(root))


    val (code, scorer) = Tree.getScorer(root)

    print(code + "\n\n")

    // Zeros.
    assert(1.0 == scorer(Array(0.0, 0.0)))

    // General two-way split test.
    assert(4.0 == scorer(Array(-5.5, 2.5)))
    assert(-3.3 == scorer(Array(-5.5, 3.5)))
    assert(-2.2 == scorer(Array(-4.5, -5.5)))
    assert(1.0 == scorer(Array(-4.5, -4.5)))

    // These hit the equalities on the thresholds.
    assert(1.0 == scorer(Array(-5.0, 3.0)))
    assert(-3.3 == scorer(Array(-5.5, 3.0)))
    assert(1.0 == scorer(Array(-4.5, 5.0)))
  }
}
