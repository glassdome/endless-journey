package test.util

import models._
import java.util.UUID

/**
 * Utility trait with functions to help in test.
 */
trait RecipeHelper {

  val dummyAuthorId = uuid.toString
  
  def newDummyRecipe[A](
       id: A,
       author: String             = dummyAuthorId,
       created: ActionAudit       = ActionAudit.now(dummyAuthorId, "created"),
       modified: ActionAudit      = ActionAudit.now(dummyAuthorId, "updated"),
       title: String              = uuid.toString,
       ingredients: Seq[Ingredient] = newDummyIngredients(3),
       instructions: String       = uuid.toString,
       servings: Int              = 1,
       totalTimeMin: Option[Int]  = None,
       activeTimeMin: Option[Int] = None,
       level: Option[String]      = None,
       tags: Seq[String] = Seq.empty,
       notes: Option[String]      = None): Recipe[A] = {

      Recipe(id, author, created, modified, title, ingredients,
      instructions, servings, totalTimeMin, activeTimeMin, level, tags, notes)
  }


  /**
    * Generate a random universally-unique identifier.
    * Note: Convert UUID toString for a guaranteed unique string.
    */
  def uuid(): UUID = UUID.randomUUID

  /**
    * Create a Seq[Ingredients] with dummy data.
    * @param n the number of Ingredients to supply in the sequence
    */
  def newDummyIngredients(n: Int): Seq[Ingredient] = {
    (1 to n) map { i => Ingredient(uuid.toString,i, Some("tbsp") , Some("prep" + i)) }
  }


}

