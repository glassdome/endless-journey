package services


import models._
import org.specs2.mutable._
import org.specs2.specification._
import org.specs2.specification.Scope

import scala.None


class MapRecipeDataSpec extends Specification {
  
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
      
      val dummyRecipe = Recipe(4, "foo", Seq(Ingredient("bar", 0, "cups")), "do-nothing", 0)
      
      // Add new recipe, ensure it returns Success(_)
      MapRecipeData.create(dummyRecipe) must beSuccessfulTry
      
      // Ensure map.size increases by 1
      MapRecipeData.rs.size must_== (oldcount + 1)
    }
    
    "fail when given a recipe with a duplicate ID" >> {
      
      val dummyRecipe = Recipe(1, "foo", Seq(Ingredient("bar", 0, "cups")), "do-nothing", 0)
      
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
      //ensure we don't have an item with ID = 6
      MapRecipeData.findById(100)  must_=== None

     }
  }  
  
  "delete" should {
    
    "remove and return the corresponding Recipe from the database given a valid ID" >> {

     // val dummyRecipe = Recipe(66, "foo", Seq(Ingredient("bar", 0, "cups")), "do-nothing", 0)
      //MapRecipeData.create(dummyRecipe)
      //delete a recipe, ensure it returns Success(_)
     // MapRecipeData.delete(66) must beSuccessfulTry
      pending
    }
    
    "throw an exception when given an ID that does not exist in the database" >> {
      // Ensure we can't delete non existing recipe (should throw an IllegalArgumentException)
      MapRecipeData.delete(100) must beFailedTry.withThrowable[IllegalArgumentException]
    }
  }
  
  "update" should {
    
    "replace the corresponding Recipe with the new representation given a valid ID" >> {
      pending
    }
    
    "throw an exception when given and ID that does not exist in the database" >> {
      pending
    }
  }
  
  "list" should {
    
    "return a list of all Recipes in the database" >> {
      pending
    }
    
    "return an empty list when the database is empty" >> {
      pending
    }
    
  }
  

  
  
}