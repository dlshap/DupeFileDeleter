package directoryManager.dupeFileDeleter.com

import main.dupeFileDeleter.com.Config

/**
 * Created by s0041664 on 6/30/2016.
 */
class FileManager {

    def defaultPattern = new Config().defaultPattern

    List getMatchingFiles(matchDirectory) {
        /* get numbered files by default(dupes) */
        def filePattern
        getMatchingFiles(matchDirectory, ~defaultPattern)
    }

    List getMatchingFiles(matchDirectory, filePattern) {
        def matchList = []
        new File(matchDirectory).eachFileMatch(filePattern) {
            matchList << it
        }
        matchList
    }

    def getOrigFileName(numFileName) {
        try {
            def matcher = (numFileName =~ defaultPattern)
            def origFile = matcher[0][1] + matcher[0][2]
            origFile
        } catch (Exception e) {
            println "bad: $numFileName"
        }
    }

    def getFileSize(fileName) {
        new File(fileName.toString()).length()
    }

    def doesFileExist(fileName) {
        new File(fileName.toString()).exists()
    }

    def deleteFile(fileName) {
        def delFile = fileName.toString()
        new File(delFile).delete()
    }
}