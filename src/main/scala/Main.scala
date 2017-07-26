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

  val rec1 = Recipe[Int](1, "rec1", ing1, "instructions1", 1)
  val rec2 = Recipe[Int](2, "rec2", ing2, "instructions2", 1)
  val rec3 = Recipe[Int](3, "rec3", ing3, "instruction3", 1)
  val rec4 = Recipe[Int](4, "rec4", ing2, "instruction4", 1)

  val myRecipes =  Seq( rec1, rec2, rec3, rec4)



  val recipes: Seq[Recipe[Int]] = Recipes.findByTitle(myRecipes, "rec1")

  for( r <- recipes) println("Recipe with title 'rec1' " + r.instruction)

  for ( r <- (Recipes.findByIngredient(myRecipes, "Chicken"))) println(Recipes.formatRecipe(r))
  
  
  import scala.collection.mutable.HashMap
  
  val rs: HashMap[Int, String] = new HashMap()
  
  rs += (1 -> "alpha")
  rs += (2 -> "beta")
  rs += (66 -> "delta")
  
  println(rs); println
  
  rs -= 66
  

  
  
  val o1: Option[String] = Some("foo")
  val o2: Option[String] = None
  
  
  val word: String = o2 match {
    case None => "123"
    case Some(w) => w
  }
  
  
  
  if (o1.isDefined) o1 else Some("N/A")
  
  val word3: String = o2 getOrElse "N/A"
  

  val word5 = o1.fold("N/A")(s => s)
  
  val word2 = Some(o1 getOrElse "N/A")
  
  val word6 = o1 orElse Some("N/A")

  def wordReader(s: Option[String]) = ???

  import scala.util.{ Try, Success, Failure }
  import scala.util.{ Either, Left, Right }

  
  val goodUsers = Seq(
      "frodo@example.com",
      "sam@example.com",
      "bilbo@example.com")

  val badUsers = Seq(
      "voldemort@example.com",
      "sauron@example.com")  
  
  
  def authenticate(user: String): Try[String] = Try {
    if (goodUsers.contains(user)) 
      "you are a good user" 
    else 
      throw new RuntimeException("AUTHENTICATE: No...you are a bad user")
  }

  def authorize(user: String): Try[String] = Try {
    if (user != "bilbo@example.com") 
      "you are authorized"
    else throw new RuntimeException("AUTHORIZE : No, bilbo")
  }

  def getData(user: String): Try[String] = Try {
    s"HERE IS YOUR DATA, $user"
  }  
  
  
  def rte(message: String) = throw new RuntimeException(message)

  def login1(user: String): String = {
    
    authenticate(user) match {
      case Failure(e) => rte("FORBIDDEN: " + e.getMessage)
      case Success(d) => {
        authorize(user) match {
          case Failure(e) => rte("FORBIDDEN: " + e.getMessage)
          case Success(d) => {
            getData(user) match {
              case Failure(e)    => rte("FORBIDDEN: " + e.getMessage)
              case Success(data) => data
            }
          }
        }
      }
    }
  }
  
  def login3(user: String): Try[String] = for {
    _    <- authenticate(user)
    _    <- authorize(user)
    data <- getData(user)
  } yield data

  
  println("-"*50)
  println("PROCESS-1:\n" + login3("baduser@example.com"))
  println("-"*50)
  println("PROCESS-1:\n" + login3("bilbo@example.com"))
  println("-"*50)
  println("PROCESS-1:\n" + login3("sam@example.com"))
  println("-"*50)
  
  def login2(user: String): String = {

    authenticate(user) match {
      case Failure(e) => rte("FORBIDDEN: " + e.getMessage)
      case Success(_) => authorize(user) match {
        case Failure(e) => rte("FORBIDDEN: " + e.getMessage)
        case Success(_) => getData(user) match {
          case Failure(e)    => rte("FORBIDDEN: " + e.getMessage)
          case Success(data) => data
        }
      }
    }
  }  
  
 
  
  
  

  
  
  case class NetworkClient(protocol: String, host: String, port: Option[Int] = None) {
    
    lazy val baseUrl = {
      val portString = port.fold(""){ p => ":%d".format(p) }
      
      //"%s://%s%s".format(protocol, host, portString)
      s"$protocol://${host}${portString}"
    }

  }
  
  


  println
  println("WORD : " + word)
  println("WORD2 : " + word2)
  println("WORD3 : " + word3)
  
  println("WORD5 : " + word5)
  println("WORD6 : " + word6)
  println
}


