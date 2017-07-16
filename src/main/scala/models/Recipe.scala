package models

case class Recipe[A](
    id: A,
    title: String, 
    ingredients: Seq[Ingredient],
    instruction: String, 
    servings: Int)

