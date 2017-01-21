package edu.spectrum.thread

import java.io.{File, IOException}

import edu.spectrum.logging.{LogPublisher => p}
import edu.spectrum.utils.{Constants, ExeFileExecutor}
import org.apache.commons.io.FileUtils

/**
  * Created by cf on 23.05.2015.
  */

class ExecuteSynthConvThread extends SynthExecutor with Runnable {

  override def run(): Unit = {
    if (!state.workspacePath.isEmpty) {
      try {
        state.saveSettings
      }
      catch {
        case e: IOException =>
          p ! ("Save Settings Files", e)
      }
      if (isSynthReady) try {
        ExeFileExecutor.synthExecute
      }
      catch {
        case e: IOException =>
          p ! ("Launch SYNTH", e)
      }
      if (isConvReady) try {
        ExeFileExecutor.convolveExecute
      }
      catch {
        case e: IOException =>
          p ! ("Launch CONV", e)
      }
      try {
        state.copySettingsFilesToWorkspace
      }
      catch {
        case e: IOException =>
          p ! ("Export Settings Files to Workspace", e)
      }
      try {
        val src: File = new File(Constants.SYNTH_RES)
        FileUtils.copyFile(src, new File(state.workspacePath + File.separator + src.getName))
        p ! ("Export SYNTH Result File to Workspace", "Result file is copied successfuly...")
      }
      catch {
        case e: IOException =>
          p ! ("Export SYNTH Result File to Workspace", e)
      }
      try {
        val src: File = new File(Constants.CONV_RES)
        FileUtils.copyFile(src, new File(state.workspacePath + File.separator + src.getName))

        p ! ("Export CONVOLVE Result File to Workspace", "Result file is copied successfuly...")
      }
      catch {
        case e: IOException =>
          p ! ("Export CONVOLVE Result File to Workspace", e)
      }
    }
    else p ! ("Launch SYNTH, CONV", "Files are not executed. Workspace was not selected!")
  }
}
