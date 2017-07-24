
package services

import models._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.specification.Scope

import scala.None
import scala.collection.mutable.HashMap
import scala.util.Try
import test.util.RecipeHelper

class RecipeServiceSpec extends Specification with RecipeHelper{

  sequential


  val dummyRecipe = newDummyRecipe(id = 10001, title = "dummy recipe 1 for RecipeService test",
    ingredients = Seq(Ingredient("bar","chopped", 1, "cups")), instructions = "do-nothing", servings = 1)

  val dummyRecipe2 = newDummyRecipe(id = 10002, title = "Beans and Rice",
    ingredients = Seq(Ingredient("bar","chopped", 1, "cups")), instructions = "do-nothing", servings = 1)

  //populate RecipeService with recipes
  val recService = RecipeService(MapRecipeData)


  "alterRecipe" should {
    "succeed when given a valid modifier" >> {
      recService.alterRecipe(dummyRecipe, 0.5) must beSuccessfulTry
    }

    "fail when given an invalid modifier" >> {
      recService.alterRecipe(dummyRecipe, 0.25) must beFailedTry.withThrowable[IllegalArgumentException]
    }
  }

  "titleExists" should {
    "return true when title exists" >> {
      recService.titleExists("Beans and Rice") must beTrue
    }

    "return false when title doesn't exist" >> {
      recService.titleExists("dummy title") must beFalse
    }
  }

  "validateRecipe" should {
    "succeed when servings has the right amount" >> {
      recService.validateRecipe(dummyRecipe) must beSuccessfulTry
    }

    "succeed when it has at least one ingredient" >> {
      recService.validateRecipe(dummyRecipe) must beSuccessfulTry
    }

    "succeed when the title doesn't exist" >> {
      recService.validateRecipe(dummyRecipe) must beASuccessfulTry
    }

    "fail validate when servings has the wrong amount" >> {
      val badServingsRec = dummyRecipe.copy( servings = -1 )
      recService.validateRecipe(badServingsRec) must beFailedTry.withThrowable[RuntimeException]
    }

    "fail validate when it doesn't have at least one ingredient" >> {
      val emptyIngredients: Seq[Ingredient] = Seq.empty[Ingredient]
      val noIngRec = dummyRecipe.copy ( ingredients = emptyIngredients)
      recService.validateRecipe(noIngRec) must beFailedTry.withThrowable[RuntimeException]
    }

    "fail validate when the title already exists" >> {
      recService.validateRecipe(dummyRecipe2) must beFailedTry.withThrowable[RuntimeException]
    }
  }

}
