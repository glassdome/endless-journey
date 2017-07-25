package models

case class Ingredient(
    name: String,
    quantity: Double, 
    measure: Option[String] = None,
    prep: Option[String] = None)