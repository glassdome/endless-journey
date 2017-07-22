object Main extends App {

  import models._
  import RecipesOp.Recipes
  
  val i1 =  Ingredient("Peppers",2)
  val i2 =  Ingredient("Mushrooms", 1, "cup")
  val i3 =  Ingredient("Chicken", 1, "cup")
  val i4 =  Ingredient("Ground Basil", 2, "tsp")
  val i5 =  Ingredient("Salt", 0.25, "tsp")
  val i6 =  Ingredient("Tomatoes", 2, "can")

  println( i1)
  println(i5)

  val ing1 = Seq( i1, i2, i5)
  val ing2 = Seq( i3, i4, i5)
  val ing3 = Seq( i1,i3, i4, i5, i6)
/*

  val rec1 = Recipe[Int](1, "rec1", ing1, "instructions1", 1)
  val rec2 = Recipe[Int](2, "rec2", ing2, "instructions2", 1)
  val rec3 = Recipe[Int](3, "rec3", ing3, "instruction3", 1)
  val rec4 = Recipe[Int](4, "rec4", ing2, "instruction4", 1)

  val myRecipes =  Seq( rec1, rec2, rec3, rec4)



  val recipes: Seq[Recipe[Int]] = Recipes.findByTitle(myRecipes, "rec1")

  for( r <- recipes) println("Recipe with title 'rec1' " + r.instruction)

  for ( r <- (Recipes.findByIngredient(myRecipes, "Chicken"))) println(Recipes.formatRecipe(r))
  
  
*/
  import scala.collection.mutable.HashMap
  
  val rs: HashMap[Int, String] = new HashMap()
  
  rs += (1 -> "alpha")
  rs += (2 -> "beta")
  rs += (66 -> "delta")
  
  println(rs); println
  
  rs -= 66
  
  println(rs)
  
}


