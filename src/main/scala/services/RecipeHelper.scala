package services

import models._

//import java.util.UUID

//import com.github.nscala_time.time.Imports._

trait RecipeHelper {

  val dummyAuthorId = AxiliaryFun.uuid.toString
  val dateTime: String = AxiliaryFun.getDateTime

  def newDummyRecipe[A](
                         id: A,
                         author: String             = dummyAuthorId,
                         created: ActionAudit       = ActionAudit(dummyAuthorId, "test", dateTime ),
                         modified: ActionAudit      = ActionAudit(dummyAuthorId, "test", dateTime),
                         title: String              = AxiliaryFun.uuid.toString,
                         ingredients: Seq[Ingredient] = newDummyIngredients(3),
                         instructions: String       = AxiliaryFun.uuid.toString,
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
    * Create a Seq[Ingredients] with dummy data.
    * @param n the number of Ingredients to supply in the sequence
    */
  def newDummyIngredients(n: Int): Seq[Ingredient] = {
    (1 to n) map { i => Ingredient(AxiliaryFun.uuid.toString, i, "tbsp") }
  }


}

