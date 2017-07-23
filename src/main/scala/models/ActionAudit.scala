package models

import java.time.ZonedDateTime

case class ActionAudit(user: String, action: String, time: ZonedDateTime ){
  
  /*
   *  TODO: Stop doing this!
   */
//  override def toString() = {
//    s"${user} - ${action} - ${time}"
//  }
}
object ActionAudit {
  
  def now(user: String, action: String): ActionAudit = {
    ActionAudit(user, action, ZonedDateTime.now)
  }
  
}