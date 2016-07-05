package tests.dupeFileDeleter.com

import directoryManager.dupeFileDeleter.com.ChooseDirectory
import directoryManager.dupeFileDeleter.com.FileManager

/**
 * Created by s0041664 on 6/30/2016.
 */
class FileManagerTest extends GroovyTestCase {

    def GETDIR = false
    def MAKETESTFILES = false

    def matchDir
    def testData = "Here is some test data."
    def testFileName = "test"
    def testFileExt = ".mp4"

    void setUp() {
        super.setUp()

        matchDir = "C:\\test\\dupes"
        if (GETDIR) {
            matchDir = new ChooseDirectory().getDirectory(matchDir)
        }
    }

    def makeFullPathName = { dirName, fname ->
        dirName + "\\" + fname + ".mp4"
    }

    def copyFile = { dirName, fromFileName, toFileName ->
        def fromFile = new File(makeFullPathName(dirName, fromFileName))
        def toFile = new File(makeFullPathName(dirName, toFileName))
        toFile.bytes = fromFile.bytes
    }

    def makeTestFiles = { testDir ->
        println "making files in '${testDir}'"
        def dir = new File(testDir)
        dir.eachFile {
            it.delete()
        }
        new File(makeFullPathName(testDir, testFileName)).text = testData
        def nextFileName = testFileName + " 2"
        copyFile(testDir, testFileName, nextFileName)
        nextFileName += "1"
        def differentTestData = testData - "data."
        new File(makeFullPathName(testDir, nextFileName)).text = differentTestData   //make different file size
        nextFileName = testFileName + " - Copy"
        copyFile(testDir, testFileName, nextFileName)
        nextFileName += " 2"
        copyFile(testDir, testFileName, nextFileName)
        nextFileName = testFileName + " - 1"
        copyFile(testDir, testFileName, nextFileName)
        nextFileName = testFileName + " 2 - Copy2"
        copyFile(testDir, testFileName, nextFileName)
        nextFileName = testFileName + " 2 - Copy 2"
        copyFile(testDir, testFileName, nextFileName)
    }


    void testGetOrigFileName() {
        def fl = new FileManager()

        def fn1 = "test 1.mp3"
        def fn2 = "test.mp3"
        assert fn2 == fl.getOrigFileName(fn1)


    }

    void testGetFileSize() {
        if (MAKETESTFILES) makeTestFiles(matchDir)
        def matchList = new FileManager().getMatchingFiles(matchDir)
        assert new File(matchList[0].toString()).length() == testData.length()
    }

    void testDoesFileExist() {
        assert new FileManager().doesFileExist("c:\\test\\dupes\\test.mp4")
    }

    void testGetMatchingFiles() {
        if (MAKETESTFILES) {
            makeTestFiles(matchDir)
        }

        def matchList = new FileManager().getMatchingFiles(matchDir)
        println "${matchList.size()} files:\n${matchList}"
        assert new FileManager().getMatchingFiles(matchDir, ~/.*/).size() == 8
        assert new FileManager().getMatchingFiles(matchDir, ~/.* .*\..*/).size() == 7
        assert new FileManager().getMatchingFiles(matchDir, ~/.* \d\..*/).size() == 4
        assert new FileManager().getMatchingFiles(matchDir, ~/.* \d*\..*/).size() == 5
        assert new FileManager().getMatchingFiles(matchDir).size() == 5
    }

    void testWeirdFileName() {
        def testDir = "D:\\My Music\\iTunes\\iTunes Media\\Music\\Jan-Hendrick Rootering, Placido Domingo,\\Luisa Miller"
        def fm = new FileManager()
        def mList = fm.getMatchingFiles(testDir)
        mList.each {fname ->
            println "num: $fname"
            println "orig: ${fm.getOrigFileName(fname)}"
        }
    }
}
