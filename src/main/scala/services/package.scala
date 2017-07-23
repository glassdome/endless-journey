
import models.ActionAudit
import java.util.UUID

package object services {

  /*
   * NOTE: We don't really need this package object because we don't need the `getDateTime` function...
   * but this is the place to put generally useful code that doesn't quite fit into any particular
   * class or object.  Any functions here will be accessible to all objects in this package - and if you
   * need the functions from another package, you can just import them like you would anything else.
   */
  
//  def getDateTime: String = {
//    import java.text.SimpleDateFormat
//    import java.util.Calendar
//
//    val today = Calendar.getInstance().getTime()
//
//    // create the date/time formatters
//    val minuteFormat = new SimpleDateFormat("mm")
//    val hourFormat = new SimpleDateFormat("hh")
//    val amPmFormat = new SimpleDateFormat("a")
//    val yearFormat = new SimpleDateFormat("yyyy")
//
//    val year = yearFormat.format(today)
//    val month = today.getMonth
//    val day = today.getDay
//
//    val currentHour = hourFormat.format(today)
//    val currentMinute = minuteFormat.format(today)
//    val amOrPm = amPmFormat.format(today)
//
//
//    val resultTime = s"${year}-${month}-${day} ${currentHour}:${currentMinute}${amOrPm}"
//    resultTime
//  }
  
}