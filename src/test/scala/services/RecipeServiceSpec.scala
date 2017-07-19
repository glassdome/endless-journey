
package services

import models._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.specification.Scope

import scala.None

import scala.util.Try
class RecipeServiceSpec extends Specification{

  // sequential

  //populate RecipeService with recipes
  val recService = RecipeService(MapRecipeData)
  val r1 = MapRecipeData.r1

  "alterRecipe" should {
    "succeed when given a valid modifier" >> {
      recService.alterRecipe(r1, 0.5) must beSuccessfulTry
    }

    "fail when given an invalid modifier" >> {
      recService.alterRecipe(r1, 0.25) must beFailedTry.withThrowable[IllegalArgumentException]
    }
  }

  "validateRecipe" should {
    "succeed when servings has the right amount" >> {
      recService.validateRecipe(r1) must beSuccessfulTry
    }
    "succeed when it has at least one ingredient" >> {
      recService.validateRecipe(r1) must beSuccessfulTry
    }
    "fail when servings has the wrong amount" >> {
      val badServingsRec = r1.copy( servings = -1 )

      recService.validateRecipe(badServingsRec) must beFailedTry.withThrowable[RuntimeException]

    }
    "fail when it doesn't have at least one ingredient" >> {
      val emptyIngredients: Seq[Ingredient] = Seq.empty[Ingredient]
      val noIngRec = r1.copy ( ingredients = emptyIngredients)

      recService.validateRecipe(noIngRec) must beFailedTry.withThrowable[RuntimeException]

    }

  }

  "titleExists" should {
    "return true when title exists" >> {
      recService.titleExists("beans and Rice") must beTrue
    }
    "return false when title doesn't exist" >> {
      recService.titleExists("dummy title") must beFalse
    }

  }


}
