package models

case class Ingredient(
    name: String,
    prep: String,
    quantity: Double, 
    measure: String="")
