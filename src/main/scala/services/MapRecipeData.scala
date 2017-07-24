package services

import models._
import scala.collection.mutable.HashMap
import services._

import scala.util.{Try, Success, Failure}


/**
 * A Recipe Database backed by a simple HashMap object.
 * 
 * As you can see - this class implements the interface defined by `trait RecipeData`.
 * Note this class is suitable only for testing as all data is stored in-memory only.
 * Also note this class loads 3 example recipies on initialization.
 */
object MapRecipeData extends RecipeData[Int] {

  // A Map acting as our Recipe[Int] data store
  private[services] val rs: HashMap[Int, Recipe[Int]] = new HashMap()
  
  
  def list(): Seq[Recipe[Int]] = {
    rs.values.toSeq
  }

  
  def findById(id: Int): Option[Recipe[Int]] = {
    rs.get(id)
  }
  
  
  def create(r: Recipe[Int], user: String): Try[Recipe[Int]] = {

    // Error if given ID already exists - no duplicates allowed.
    if (rs.contains(r.id)) 
      Failure(new IllegalArgumentException(s"Recipe with ID $r.id already exists."))
    else {
      val timestamp = ActionAudit.now(user, "created")
      Success(concat(r.copy(created = timestamp, modified = timestamp)))
    }
  }


  def update(r: Recipe[Int], user: String): Try[Recipe[Int]] = {
    
    // Error if the given recipe doesn't exist (can't update nothing)
    if (notFound(r.id))
      Failure(new IllegalArgumentException(s"Recipe with ID $r.id not found."))
    else {
      Success(r.copy(modified = ActionAudit.now(user, "updated")))
    }
  }

  
  def delete(id: Int, user: String): Try[Recipe[Int]] = {
    
    // Error if the given recipe doesn't exist.
    if (notFound(id)) 
      Failure(new IllegalArgumentException(s"Recipe with ID $id not found."))
    else Success { 
      val result = rs(id)
      rs -= id
      result.copy(modified = ActionAudit.now(user, "deleted"))
    }
  }
  
  //
  // PRIVATE FUNCTIONS
  //
  
  
  private def recipeExists(id: Int) = rs.contains(id)
  private def notFound(id: Int) = rs.get(id).isEmpty
  
  /**
   * Add a Recipe to the database and return the Recipe.
   * Note: Concatenating to the same ID in the map *replaces* the existing item.
   */
  private def concat(r: Recipe[Int]) = {
    rs += (r.id -> r)
    r
  }
  

  /*
   * This is a cheat for our little test scenario.  The code below will run once, as soon as the object is loaded, so
   * we'll always have these three Recipes in the database at startup.
   */
  private val dummyUser = "noone@example.com"
  
  val r1 = Recipe(1, 
      author       = "noone@example.com",
      created      = ActionAudit.now(dummyUser, "created"),
      modified     = ActionAudit.now(dummyUser, "created"),
      title        = "Beans and Rice",
      ingredients  = Seq(Ingredient("white rice", "boiled", 1, "cup"), Ingredient("black beans", "cooked", 15, "oz")),
      instructions = "Boil the rice. Heat up the beans. Mix together and eat.",
      servings     = 4)

  val r2 = Recipe(2, 
      author       = "noone@example.com",
      created      = ActionAudit.now(dummyUser, "created"),
      modified     = ActionAudit.now(dummyUser, "created"),      
      title       = "Tofu and Vegetable Stir Fry",
      ingredients = Seq(
          Ingredient("firm tofu","raw", 16, "oz"),
          Ingredient("red bell peppers","chopped", 2, "unit"),
          Ingredient("shitake mushrooms","sliced", 1, "lb"),
          Ingredient("white rice", "boiled", 1, "cup"),
          Ingredient("soy sauce", "raw", 1, "tbsp")),
      instructions = "Tofu and vegetables in a wok. rice in a pot. Eat it.",
      servings    = 4)
  
  val r3 = Recipe(3,
      author       = "noone@example.com",
      created      = ActionAudit.now(dummyUser, "created"),
      modified     = ActionAudit.now(dummyUser, "created"),      
      title       = "Basic Quinoa",
      ingredients = Seq(Ingredient("quinoa", "cooked", 1, "cup"), Ingredient("water", "boiled", 2, "cups")),
      instructions = "Boil water, add quinoa. Turn heat to low and simmer for 15 minutes or until water has evaporated.",
      servings    = 4)  
  
  rs += (r1.id -> r1)
  rs += (r2.id -> r2)
  rs += (r3.id -> r3)
}

