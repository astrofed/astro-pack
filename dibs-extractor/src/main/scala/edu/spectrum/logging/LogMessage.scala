package edu.spectrum.logging

import java.util.logging.{Level, LogRecord}

/**
  * Created by <Fedor Chekhonadskikh> on 21.01.17.
  */
case class LogMessage(
                     level: Level,
                     msg: String,
                     name: Option[String]
                     ) extends LogRecord(level, msg) {
  name.foreach(setLoggerName)
}
