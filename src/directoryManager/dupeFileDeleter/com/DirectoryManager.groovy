package directoryManager.dupeFileDeleter.com
/**
 * Created by s0041664 on 6/29/2016.
 */
class DirectoryManager {
    def getAllSubdirectories(currentDir) {
        def subDirectoryList = []
        def curDir = new File(currentDir)
        if (curDir.exists()) {
            new File(currentDir).eachDirRecurse() { dir ->
                def subName = dir.toString()
                subDirectoryList << subName
            }
        }
        subDirectoryList
    }
}
