package edu.spectrum.logging

import java.util.logging._

import edu.spectrum.gui.LogWindow

import scala.util.{Success, Try}

/**
  * Created by <Fedor Chekhonadskikh> on 21.01.17.
  */
private class LoggingWindowHandler extends Handler {

  val window = new LogWindow("Logging window", 640, 200)

  override def flush(): Unit = {}

  override def publish(record: LogRecord): Unit =
    if (isLoggable(record))
      window.showInfo(Option(getFormatter.format(record)))

  override def close(): Unit = {}

  /**
    * private method constructing a Filter object with the filter name.
    *
    * @param filterName
    * the name of the filter
    * @return the Filter object
    */
  private def makeFilter(filterName: String) = {
    val filter = Try {
      val c = Class.forName(filterName)
      c.newInstance.asInstanceOf[Filter]
    } match {
      case Success(f) =>
        Some(f)
      case _ =>
        System.out.println("There was a problem to load the filter class: " + filterName)
        None
    }

    filter.orNull
  }

  /**
    * private method creating a Formatter object with the formatter name. If no
    * name is specified, it returns a SimpleFormatter object
    *
    * @param formatterName
    * the name of the formatter
    * @return Formatter object
    */
  private def makeFormatter(formatterName: String) =
    Try {
      val c = Class.forName(formatterName)
      c.newInstance.asInstanceOf[Formatter]
    } match {
      case Success(f) =>
        f
      case _ =>
        System.out.println("There was a problem to load the formatter class: " + formatterName)
        new SimpleFormatter
    }

  {
    val manager = LogManager.getLogManager
    val className = this.getClass.getName
    val level = manager.getProperty(className + ".level")
    val filter = manager.getProperty(className + ".filter")
    val formatter = manager.getProperty(className + ".formatter")
    // accessing super class methods to set the parameters
    setLevel(Level.INFO)
    setFilter(makeFilter(filter))
    setFormatter(makeFormatter(formatter))
  }
}
