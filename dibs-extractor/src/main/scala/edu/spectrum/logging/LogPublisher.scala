package edu.spectrum.logging

import java.util.logging.{Level, Logger}

/**
  * Created by <Fedor Chekhonadskikh> on 21.01.17.
  */
object LogPublisher {

  private val handler = new LoggingWindowHandler()
  private val logger = Logger.getLogger("logging.handler")

  logger.addHandler(handler)

 def openWindow() = handler.window.setVisible(true)

  def !(message: Any): Unit = message match {
    case msg: String => logger.info(msg)
    case (title: String, e: Throwable) => handler.publish(new LogMessage(Level.WARNING, e.getMessage, Some(title)))
    case (title: String, msg: String) => handler.publish(new LogMessage(Level.INFO, msg, Some(title)))
    case _ =>
  }

}
