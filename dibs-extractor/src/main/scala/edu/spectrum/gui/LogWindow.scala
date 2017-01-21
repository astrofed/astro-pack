package edu.spectrum.gui

import javax.swing.{JFrame, JScrollPane, JTextArea}

/**
  * Created by <Fedor Chekhonadskikh> on 21.01.17.
  */
class LogWindow(title: String, width: Int, height: Int) extends JFrame(title) {
  private val textArea = new JTextArea
  private val pane =  new JScrollPane(textArea)

  {
    setSize(width, height)
    getContentPane.add(pane)
    setVisible(false)
  }

  /**
    * This method appends the data to the text area.
    *
    * @param data
    * the Logging information data
    */
  def showInfo(data: Option[String]) =
    data.foreach{
      d =>
        textArea.append(d)
        this.getContentPane.validate()
    }

}
