package models

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