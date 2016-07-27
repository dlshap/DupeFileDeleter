package main.renameFiles

import directoryManager.dupeFileDeleter.com.ChooseDirectory
import directoryManager.dupeFileDeleter.com.FileManager
import main.dupeFileDeleter.com.Config

/**
 * Created by s0041664 on 7/1/2016.
 */
class RenameBracketFiles {

    def defaultPattern = /(.*)\[.*\].csv/

    def chooseRootDirectory() {
        def rootDir = new Config().rootDir
        rootDir = new ChooseDirectory().getDirectory(rootDir)
        rootDir
    }

    def getBracketedFilenameList(rootDir) {
        def fileList = []
        fileList = new FileManager().getMatchingFiles(rootDir, defaultPattern)
    }

    def renameBracketFiles(rootDir) {
        def renameList = getBracketedFilenameList(rootDir)

        println "Directory: $rootDir..."
        println renameList.size()
        renameList.each {
//            println it
            def oldName = it.toString()
            def matcher = (oldName =~ defaultPattern)
//            def origFile = matcher[0][1] + matcher[0][2]
            println "old name:" + oldName
            def newName = matcher[0][1] + ".csv"
            println "new name:" + newName
//            new File(oldName).renameTo(new File(newName))
        }
        renameList.size()
    }

    def runMain() {
        def rootDir = chooseRootDirectory()
        def renameCount = renameBracketFiles(rootDir)
        println "$renameCount files renamed"
    }

    static main(args) {
        def app = new RenameBracketFiles()
        app.runMain()
    }

}
