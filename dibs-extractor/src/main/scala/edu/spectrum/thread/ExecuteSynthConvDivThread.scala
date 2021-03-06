package edu.spectrum.thread

import java.io.{File, IOException}

import edu.spectrum.logging.{LogPublisher => p}
import edu.spectrum.utils.{Constants, Divisor, ExeFileExecutor, FileWorker}
import org.apache.commons.io.FileUtils

class ExecuteSynthConvDivThread extends SynthExecutor with Runnable {
  override def run(): Unit = {
    if (!state.workspacePath.isEmpty) {
      try {
        state.saveSettings
      } catch {
        case e: IOException =>
          p ! ("Save Settings Files", e)
      }
      if (isSynthReady) try {
        ExeFileExecutor.synthExecute
      } catch {
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
      if (isDivisorReady) {
        try {
          FileWorker.write(Constants.DIV_RES, Divisor.divide(Constants.OBS_DATA, Constants.CONV_RES))
        } catch {
          case e: Exception =>
            p ! ("Divide OBS and SYNT_RES (with interpolation on OBS)", e)
        }
      }
      try {
        state.copySettingsFilesToWorkspace
      } catch {
        case e: IOException =>
          p ! ("Export Settings Files to Workspace", e)
      }
      try {
        val src: File = new File(Constants.DIV_RES)
        FileUtils.copyFile(src, new File(state.workspacePath + File.separator + src.getName))
        p ! ("Export Result File to Workspace", "Result file is copied successfuly...")
      } catch {
        case e: IOException =>
          p ! ("Export Result File to Workspace", e)
      }
    }
    else p ! ("Launch SYNTH, CONV, DIVISOR", "Files are not executed. Workspace was not selected!")
  }

  protected def isDivisorReady: Boolean = isExist(Constants.OBS_DATA) && isExist(Constants.CONV_RES)

}

