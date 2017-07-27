package RecipesOp


import io.glassdome.recipeapp.models._

object Recipes {

  def findByIngredient(recipes: Seq[Recipe[Int]], targetIngredient: String): Seq[Recipe[Int]] =
    recipes filter {r =>  ( hasIngredient(r.ingredients, targetIngredient))}

  def hasIngredient(ingredients: Seq[Ingredient], targetIngredient: String): Boolean =
  // !(ingredients filter ( t => t.name == targetIngredient)).isEmpty   //avoid inverted logic
    ingredients exists {_.name.toLowerCase() == targetIngredient.toLowerCase()}

  def findByTitle(recipes: Seq[Recipe[Int]], text: String):Seq[Recipe[Int]] =
    recipes filter (r => r.title.contains(text))

  def formatIngredient(i: Ingredient): String = {
    s"${i.quantity} ${i.measure} ${i.name.capitalize}"
  }

  def formatRecipe(r: Recipe[Int]): String = {

    val sep = "-"*40

    val ins = r.ingredients.foldLeft(""){ (acc, next) =>

      "%s\n *  %s".format(acc, formatIngredient(next))

    }

    s"""

       |$sep

       |${r.title.toUpperCase} (serves: ${r.servings})

       |--

       |[ingredients]: ${ins}

       |

      |[instruction]:

       |  ${r.instruction}

       |$sep

      """.stripMargin

  }

  /*
  def multiplyRecipe(recipe: Recipe, multiplier: Double): Recipe = {
     val convertedIngredients: Seq[Ingredient] = for(i <- recipe.ingredients) yield
     { Ingredient(i.name, i.quantity * multiplier, i.measure)}

     Recipe(recipe.title, convertedIngredients,recipe.instruction, recipe.servings)
  }
  */

  def multiplyRecipe(recipe: Recipe[Int], multiplier: Double): Recipe[Int] = {
    var converted = recipe.ingredients map {i => i.copy(quantity = (i.quantity * multiplier))}
    recipe.copy(ingredients = converted, servings = (recipe.servings* multiplier.toInt))
  }

  def normalizeMeasure(m: String) = m.trim.toLowerCase match {
    case "cup" => "c"
    case "tbsp" => "Tbsp"
    case _  => m
  }


}

