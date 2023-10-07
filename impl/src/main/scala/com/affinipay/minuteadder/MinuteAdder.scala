package com.affinipay.minuteadder

object MinuteAdder {

  private val TIME_PATTERN = """(1[0-2]|[1-9]):([0-5]\d) (AM|PM)""".r
  private val AM = "AM"
  private val PM = "PM"

  private def throwException =
    throw new IllegalArgumentException("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")

  private def convertToMinutes(hour: String, minute: String, ampm: String) = {
    val totalMinutes = hour.toInt * 60 + minute.toInt
    ampm match {
      case AM => totalMinutes
      case _ => totalMinutes + 12 * 60
    }
  }

  private def timeToMinutes(time: String): Int =
    time match {
      case TIME_PATTERN(hour, minute, ampm) => convertToMinutes(hour, minute, ampm)
      case _ => throwException
    }

  private def getAMOrPMOfHours(hour: Int) =
    if (hour < 12) AM else if (hour >= 24) AM else PM

  private def hoursToFormat(hour: Int) =
    if (hour == 0) 12 else if (hour > 24) hour - 12 else if (hour > 12) hour - 12 else hour

  private def minutesToTime(minutes: Int): String = {
    val hour = minutes / 60
    val minute = minutes % 60
    f"${hoursToFormat(hour)}%1d:$minute%02d ${getAMOrPMOfHours(hour)}"
  }

  def addMinutes(time: String, minutes: Int): String = {
    val currentTimeInMinutes = timeToMinutes(time)
    minutesToTime(currentTimeInMinutes + minutes)
  }
  
}