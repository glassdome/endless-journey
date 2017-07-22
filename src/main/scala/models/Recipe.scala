package models

//import com.github.nscala_time.time.Imports._

case class ActionAudit(user: String, action: String, time: String ){
  override def toString() = {
    s"${user} - ${action} - ${time}"
  }
}
/*
case class Recipe[A](
    id: A,
    title: String, 
    ingredients: Seq[Ingredient],
    instruction: String, 
    servings: Int)
*/

case class Recipe[A](
                      id: A,
                      author: String,
                      created: ActionAudit,
                      modified: ActionAudit,
                      title: String,
                      ingredients: Seq[Ingredient],
                      instructions: String,
                      servings: Int,
                      totalTimeMin: Option[Int]  = None,
                      activeTimeMin: Option[Int] = None,
                      level: Option[String]      = None,
                      tags: Seq[String]          = Seq.empty,
                      notes: Option[String]      = None)