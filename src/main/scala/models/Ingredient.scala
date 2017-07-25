package models

case class Ingredient(
    name: String,
    quantity: Double, 
    measure: String="",
    prep: Option[String] = None)