package services

import java.util.UUID

object AxiliaryFun {

  /**
    * Generate a random universally-unique identifier.
    * Note: Convert UUID toString for a guaranteed unique string.
    */
  def uuid(): UUID = UUID.randomUUID


  def getDateTime: String = {
    import java.text.SimpleDateFormat
    import java.util.Calendar

    val today = Calendar.getInstance().getTime()

    // create the date/time formatters
    val minuteFormat = new SimpleDateFormat("mm")
    val hourFormat = new SimpleDateFormat("hh")
    val amPmFormat = new SimpleDateFormat("a")
    val yearFormat = new SimpleDateFormat("yyyy")

    val year = yearFormat.format(today)
    val month = today.getMonth
    val day = today.getDay

    val currentHour = hourFormat.format(today)
    val currentMinute = minuteFormat.format(today)
    val amOrPm = amPmFormat.format(today)


    val resultTime = s"${year}-${month}-${day} ${currentHour}:${currentMinute}${amOrPm}"
    resultTime
  }

}
