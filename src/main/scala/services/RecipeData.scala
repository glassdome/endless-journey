package services


import models._
import scala.util.{Try, Success, Failure}

/**
 * Simple data access interface for CRUD operations against recipes in a data store.
 * 
 * @tparam A the datatype to use for Recipe[A].id (i.e., Int, UUID)
 */
trait RecipeData[A] {
  
  /**
   * Get a list of all Recipes
   */
  def list(): Seq[Recipe[A]]
  
  /**
   * Get a single Recipe[A] by ID
   * 
   * @param id the ID of the Recipe[A] to retrieve
   * @return an Option containing the specified Recipe[A] if found
   */
  def findById(id: A): Option[Recipe[A]]

  /**
   * Create a new Recipe[A]
   * 
   * @param r the new Recipe[A] to persist
   * @return the newly created Recipe[A]
   */
  def create(r: Recipe[A]): Try[Recipe[A]]
  
  /**
   * Update an existing Recipe[A]
   * 
   * @param r the modified Recipe[A] to persist
   * @return the updated Recipe[A]
   */
  def update(r: Recipe[A]): Try[Recipe[A]]
  
  /**
   * Delete a recipe from the database
   * 
   * @param id the ID of the Recipe[A] to delete
   * @return the recipe that was deleted
   */
  def delete(id: A): Try[Recipe[A]]
}

