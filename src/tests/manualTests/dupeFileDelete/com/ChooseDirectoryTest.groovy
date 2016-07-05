package tests.manualTests.dupeFileDelete.com

import main.dupeFileDeleter.com.Config
import directoryManager.dupeFileDeleter.com.ChooseDirectory

/**
 * Created by s0041664 on 6/30/2016.
 */
class ChooseDirectoryTest extends GroovyTestCase {
    void testGetDirectory() {
        println new ChooseDirectory().getDirectory(new Config().rootDir)
    }
}
