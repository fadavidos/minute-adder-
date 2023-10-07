package com.affinipay.minuteadder

object Main {

  def main(args: Array[String]): Unit = {
    println(s"Running Minute Adder with ${args.size} args: ${args.mkString(",")}")
    if (args.length != 2) {
      println("You must provide exactly two arguments: a String and an Int.")
    } else {
      val time = args(0)
      val minutes = args(1).toInt
      println(s"Your answer ${MinuteAdder.addMinutes(time, minutes)}")
    }
  }
}