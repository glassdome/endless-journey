package services


import models._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.specification.Scope

import scala.None

import scala.util.Try


class MapRecipeDataSpec extends Specification with RecipeHelper {

  sequential

  "constructor" should {
    /*
     * This will test our initialization code. Ensure the three 'seed' recipes load at startup.
     */
    "load 3 'seed' recipes at startup" >> {

      // Ensure we have three recipes
      MapRecipeData.rs.size must_== 3

      // Ensure they have IDs 1, 2, and 3
      (MapRecipeData.rs.keys).toSeq.sorted must_=== List(1, 2, 3)
    }
  }

  /*
   * All tests for the `create` method go in here.
   */
  "create" should {

    "succeed when given a valid recipe" >> {
      val oldcount = MapRecipeData.rs.size

      //val dummyRecipe = newDummyRecipe(4)
      val dummyRecipe = newDummyRecipe(id= 4, title = "foo",
        ingredients = Seq(Ingredient("bar", 0, "cups")), instructions = "do-nothing", servings = 0)

      // Add new recipe, ensure it returns Success(_)
      MapRecipeData.create(dummyRecipe) must beSuccessfulTry

      // Ensure map.size increases by 1
      MapRecipeData.rs.size must_== (oldcount + 1)
    }

    "fail when given a recipe with a duplicate ID" >> {

      val dummyRecipe = newDummyRecipe(id = 1, "foo",
        ingredients = Seq(Ingredient("bar", 0, "cups")), instructions = "do-nothing", servings = 0)

      // Ensure we have an item with ID == 1
      MapRecipeData.rs.contains(1) must beTrue

      // Ensure we can't replace or duplicate the item (should throw an IllegalArgumentException)
      MapRecipeData.create(dummyRecipe) must beFailedTry.withThrowable[IllegalArgumentException]
    }
  }



  "findById" should {

    "return the corresponding Recipe given an ID that exists in the database" >> {
      //ensure we have a recipe with ID = 1
      MapRecipeData.findById(1)  must_=== Some(MapRecipeData.rs(1))

    }

    "return None when given an ID that does not exist in the database" >> {
      //ensure we don't have an item with ID = 100 which doesn't exist
      MapRecipeData.findById(100)  must_=== None

    }
  }

  "delete" should {

    "remove and return the corresponding Recipe from the database given a valid ID" >> {

      val dummyRecipe = newDummyRecipe(id = 66, title = "new foo",
        ingredients =Seq(Ingredient("bar", 0, "cups")), instructions = "do-nothing", servings = 0)
      MapRecipeData.create(dummyRecipe) must beSuccessfulTry
      MapRecipeData.delete(66) must beSuccessfulTry
    }

    "throw an exception when given an ID that does not exist in the database" >> {
      // Ensure we can't delete non existing recipe (should throw an IllegalArgumentException)
      MapRecipeData.delete(1000) must beFailedTry.withThrowable[IllegalArgumentException]
    }
  }

  "update" should {

    "replace the corresponding Recipe with the new representation given a valid ID" >> {

      val dummyRecipe = MapRecipeData.findById(1).get
      val oldIngCount = dummyRecipe.ingredients.size
      val newIngredients = dummyRecipe.ingredients :+ Ingredient("blah", 23, "tsp")
      val updatedRec = dummyRecipe.copy(ingredients = newIngredients)

      updatedRec.ingredients.size === oldIngCount + 1

      val result = MapRecipeData.update(updatedRec)
      result must beSuccessfulTry
      result.get.ingredients.size === oldIngCount + 1

    }

    "throw an exception when given and ID that does not exist in the database" >> {
      val dummyRecipe = newDummyRecipe(id = 1000, title = "bad foo",
        ingredients = Seq(Ingredient("bar", 0, "cups")), instructions = "do-nothing", servings = 0)
      MapRecipeData.update(dummyRecipe) must beFailedTry.withThrowable[IllegalArgumentException]
    }
  }

  "list" should {

    "return a list of all Recipes in the database" >> {
      //ensures we have a seq of all the items in MapRecipeData

      val keys = MapRecipeData.rs.keys.toSeq
      val ids = MapRecipeData.list.map(_.id)

      keys == ids must beTrue


    }

    "return an empty list when the database is empty" >> {
      //ensures we have an empty list if all items are deleted

      // Map over recipes, cache delete result of each.
      val deleted: Seq[Try[Recipe[Int]]] = (MapRecipeData.rs.map { case (k, v) => MapRecipeData.delete(k) }).toSeq

      // Ensure ALL deletes succeeded
      deleted exists { _.isFailure } must beFalse

      // Now test the method
      MapRecipeData.list() must beEmpty

    }

  }


}