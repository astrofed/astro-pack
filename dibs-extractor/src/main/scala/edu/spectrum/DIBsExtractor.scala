package edu.spectrum

import java.awt.Frame
import javax.swing._

import edu.spectrum.gui.MainFrame
import edu.spectrum.logging.{LogPublisher => p}

object DIBsExtractor extends App {
    try {
      UIManager
        .getInstalledLookAndFeels
        .find(_.getName == "Windows")
        .foreach(info => UIManager.setLookAndFeel(info.getClassName))
    }
    catch {
      case ex: Throwable =>
        p ! ("SYNTHelper - main", ex)
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(
      new Runnable() {
        def run() {
          val app: MainFrame = new MainFrame
          app.setVisible(true)
          app.setExtendedState(app.getExtendedState | Frame.MAXIMIZED_BOTH)
        }
      })
}