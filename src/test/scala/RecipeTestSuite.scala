import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class RecipeTestSuite extends FunSuite {

  import RecipesOp._

  trait TestSets {

    val i1 =  Ingredient("Peppers",2)
    val i2 =  Ingredient("Mushrooms", 1, "cup")
    val i3 =  Ingredient("Chicken", 1, "cup")
    val i4 =  Ingredient("Ground Basil", 2, "tsp")
    val i5 =  Ingredient("Salt", 0.25, "tsp")
    val i6 =  Ingredient("Tomatoes", 2, "can")

    val ing1 = Seq( i1, i2, i5)
    val ing2 = Seq( i3, i4, i5)
    val ing3 = Seq( i1,i2, i3, i4, i5, i6)

  }

/*
  test("Recipe has ingredient") {

    new TestSets {

      assert(Recipes.hasIngredient(ing1, "Mushrooms"), "Has Mushrooms")
      assert(!Recipes.hasIngredient(ing3, "Mushrooms"), "Ing seq doesnt'have Mushrooms")
      assert(Recipes.hasIngredient(ing2, "Chicken"), "Has Chicken")
    }
  }
 */

}

