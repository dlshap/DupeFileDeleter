package tests.dupeFileDeleter.com

import main.dupeFileDeleter.com.Config
import main.dupeFileDeleter.com.DeleteDuplicateFiles

/**
 * Created by s0041664 on 7/1/2016.
 */
class DeleteDuplicateFilesTest extends GroovyTestCase {

    def testDir = "C:\\test\\dupes"

    void setUp() {
        super.setUp()
    }

    void testRemoveUnmatchedFiles() {
        def dm = new DeleteDuplicateFiles()
        def numList = dm.getNumberedFileList(testDir)
        def delList = dm.removeUnmatchedFiles(testDir, numList)

        println numList.size()
        println numList
        println delList.size()
        println delList
    }

    void testDeleteFiles() {
        def dm = new DeleteDuplicateFiles()
        def numList = dm.getNumberedFileList(testDir)
        assert numList.size() == 5
        def delList = ["C:\\test\\dupes\\test 21.mp4", "C:\\test\\dupes\\test 2.mp4"]
        dm.deleteFiles(delList)
        numList = dm.getNumberedFileList(testDir)
        assert numList.size() == 3
    }
}