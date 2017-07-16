package services

import models._
import scala.util.{Try, Success, Failure}


/*
 * Here is where all the code to manipulate Recipes belongs.
 */
class RecipeService[A](db: RecipeData[A]) {
  
  def alterRecipe(recipe: Recipe[Int], multiplier: Double): Try[Recipe[Int]] = Try {
    // Ensure we get a reasonable multiplier
    if (!validMultiplier(multiplier)) 
      throw new IllegalArgumentException(s"Multiplier must be between .5 and 10 in .5 increments. found: $multiplier")
    else {
      val converted = recipe.ingredients map { i => i.copy(quantity = (i.quantity * multiplier)) }
      recipe.copy(ingredients = converted, servings = (recipe.servings*multiplier.toInt))
    }
  }
  
  /**
   * Validate new input Recipe
   */
  def validateRecipe(r: Recipe[Int]): Try[Recipe[Int]] = Try {
    if (r.servings <= 0) throw new RuntimeException(s"Servings must be > 0. found: ${r.servings}")
    if (r.ingredients.isEmpty) throw new RuntimeException(s"Must supply at least one ingredient.")
    r
  }
  
  /**
   * Determine if any Recipe in the database has the given title.
   */
  def titleExists(title: String): Boolean = {
    db.list exists { r => normal(r.title) == normal(title) } 
  }
  
  private[services] def normal(s: String) = s.trim.toLowerCase
  
  private[services] def validMultiplier(in: Double): Boolean = {
    (.5 to 10 by .5).contains(in)
  }
}

object RecipeService {
  def apply[A](db: RecipeData[A]): RecipeService[A] = new RecipeService[A](db)
}
