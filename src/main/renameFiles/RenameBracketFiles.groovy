package main.renameFiles

import directoryManager.dupeFileDeleter.com.ChooseDirectory
import directoryManager.dupeFileDeleter.com.FileManager
import main.dupeFileDeleter.com.Config

/**
 * Created by s0041664 on 7/1/2016.
 */
class RenameBracketFiles {

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

    def renameBracketFiles(rootDir) {
        def renameList = getNumberedFileList(rootDir)
        def unMatchedFileList = removeUnmatchedFiles(rootDir, renameList)

        /* NO TURNING BACK */
//        if (DELETEFILES) deleteFiles(unMatchedFileList)
        /*                 */

        println "Directory: $rootDir..."
        println renameList.size()
        println unMatchedFileList.size()
        renameList.each {
//            println it
            def oldName = it.toString()
            def defaultPattern = /(.*)\[.*\].csv/
            def matcher = (oldName =~ defaultPattern)
//            def origFile = matcher[0][1] + matcher[0][2]
            println "old name:"+oldName
            def newName = matcher[0][1]+".csv"
            println "new name:"+newName
            new File(oldName).renameTo(new File(newName))
        }
        unMatchedFileList.size()

        /*
        file.renameTo 'newname.groovy'
        OR
        String oldFilename = "old.txt"
        String newFilename = "new.txt"

        new File(oldFilename).renameTo(new File(newFilename))
         */
    }

    def runMain() {
        def renameCount = 0
        def rootDir = chooseRootDirectory()
        renameCount = renameBracketFiles(rootDir)
//        def dirList = []
//        if (rootDir != null) {
//            dirList = new DirectoryManager().getAllSubdirectories(rootDir)
//            dirList.each { dir->
//                deleteFileCount += renameBracketFiles(dir)
//            }
//        }
//        println "total directories: ${dirList.size()}"
        println "total files to delete: $renameCount"
    }

    static main(args) {
        def app = new RenameBracketFiles()
        app.runMain()
    }

}
