package tests.dupeFileDeleter.com

import directoryManager.dupeFileDeleter.com.DirectoryManager

/**
 * Created by DavidS on 7/4/2016.
 */
class DirectoryManagerTest extends GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    def makeTestDirectories = {
        new File("C:\\test\\dupes").mkdir()
        new File("C:\\test\\tests1\\tests1s1").mkdirs()
        new File("C:\\test\\tests2\\tests2s1").mkdirs()
        new File("C:\\test\\tests2\\tests2s2").mkdirs()
        new File("C:\\test\\tests2\\tests2s2\\tests2s1").mkdirs()
        new File("C:\\test\\tests2\\tests2s2\\tests2s2").mkdirs()
        new File("C:\\test\\tests2\\tests2s3").mkdirs()
    }

    void testGetAllSubdirectories() {
        makeTestDirectories()
        def dirList = new DirectoryManager().getAllSubdirectories("c:\\test")
        assert dirList.size() == 9
    }
}
