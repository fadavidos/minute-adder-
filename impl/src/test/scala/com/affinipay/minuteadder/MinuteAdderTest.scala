package com.affinipay.minuteadder

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MinuteAdderTest extends AnyFunSpec with Matchers {
  
  describe("addMinutes") {
    it("should be implemented") {
      noException should be thrownBy MinuteAdder.addMinutes("5:02 PM", 1)
    }

    it("should add minutes") {
      MinuteAdder.addMinutes("5:02 PM", 1) should be("5:03 PM")
    }

    it("should add a lot of minutes") {
      MinuteAdder.addMinutes("5:02 PM", 60) should be("6:02 PM")
    }

    it("should allow a hour o'clock") {
      MinuteAdder.addMinutes("8:00 PM", 62) should be("9:02 PM")
    }

    it("should allow the addition of minutes from AM to PM") {
      MinuteAdder.addMinutes("9:13 AM", 200) should be("12:33 PM")
    }

    it("should allow the addition of minutes from PM to AM") {
      MinuteAdder.addMinutes("9:13 PM", 200) should be("12:33 AM")
    }

    it("should not allow hours greater than 12") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("15:02 PM", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow minutes greater than 59") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("3:60 AM", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow minutes less than 10") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("1:6 AM", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow a value different of AM or PM") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("3:06 TM", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow a value of am") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("3:06 am", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow a value of pm") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("3:06 pm", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow a value of pM") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("3:06 pM", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

    it("should not allow hours in 0") {
      val exception = intercept[IllegalArgumentException] {
        MinuteAdder.addMinutes("00:06 PM", 60)
      }
      exception.getMessage should be("Invalid time format. The valid format is [H]H:MM {AM|PM} in a 12-hour format")
    }

  }
}