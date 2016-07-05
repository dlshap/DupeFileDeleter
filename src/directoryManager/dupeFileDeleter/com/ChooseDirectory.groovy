package directoryManager.dupeFileDeleter.com

import javax.swing.filechooser.FileFilter
import javax.swing.JFileChooser

/**
 * Created by s0041664 on 6/30/2016.
 */
class ChooseDirectory {

    def getDirectory(initialPath) {

        def openFileDialog = new JFileChooser(
                dialogTitle: "Choose a directory",
                fileSelectionMode: JFileChooser.DIRECTORIES_ONLY
        )

        openFileDialog.setCurrentDirectory(new File(initialPath))
        openFileDialog.showOpenDialog()
        def selectedFile = openFileDialog.getSelectedFile()
        def selectedPath = null
        if (selectedFile != null)
             selectedPath = openFileDialog.getSelectedFile().getAbsolutePath()
        selectedPath
    }
}
