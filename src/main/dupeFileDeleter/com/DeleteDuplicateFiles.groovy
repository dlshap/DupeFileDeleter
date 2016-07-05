package main.dupeFileDeleter.com

import directoryManager.dupeFileDeleter.com.ChooseDirectory
import directoryManager.dupeFileDeleter.com.DirectoryManager
import directoryManager.dupeFileDeleter.com.FileManager

/**
 * Created by s0041664 on 7/1/2016.
 */
class DeleteDuplicateFiles {

    def DELETEFILES = false

    def chooseRootDirectory() {
        def rootDir = new Config().rootDir
        rootDir = new ChooseDirectory().getDirectory(rootDir)
        rootDir
    }

    def getNumberedFileList(rootDir) {
        def fileList = []
        fileList = new FileManager().getMatchingFiles(rootDir)
    }

    def removeUnmatchedFiles(fileDir, numFileList) {
        /* remove numbered files that don't have matching un-numbered file OR
           are a different size than un-numbered file
         */
        def deleteFileList = []
        def fileManager = new FileManager()
        numFileList.each {
            def numName = it.toString()
            def origName = fileManager.getOrigFileName(it)
            if (fileManager.doesFileExist(origName) &&
                    (fileManager.getFileSize(numName) == fileManager.getFileSize(origName)))
                deleteFileList << it
        }
        deleteFileList
    }

    def deleteFiles(delFileList) {
        delFileList.each {
            new FileManager().deleteFile(it)
        }
    }

    def deleteMatchingNumberedFiles(rootDir) {
        def numFileList = getNumberedFileList(rootDir)
        def unMatchedFileList = removeUnmatchedFiles(rootDir, numFileList)

        /* NO TURNING BACK */
        if (DELETEFILES) deleteFiles(unMatchedFileList)
        /*                 */

        println "Directory: $rootDir..."
        println numFileList.size()
        println unMatchedFileList.size()
        unMatchedFileList.each {
            println it
        }
        unMatchedFileList.size()
    }

    def runMain() {
        def deleteFileCount = 0
        def rootDir = chooseRootDirectory()
        def dirList = []
        if (rootDir != null) {
            dirList = new DirectoryManager().getAllSubdirectories(rootDir)
            dirList.each { dir->
                deleteFileCount += deleteMatchingNumberedFiles(dir)
            }
        }
        println "total directories: ${dirList.size()}"
        println "total files to delete: $deleteFileCount"
    }

    static main(args) {
        def app = new DeleteDuplicateFiles()
        app.runMain()
    }

}
