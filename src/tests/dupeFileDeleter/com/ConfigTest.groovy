package tests.dupeFileDeleter.com

import main.dupeFileDeleter.com.Config

/**
 * Created by s0041664 on 6/30/2016.
 */
class ConfigTest extends GroovyTestCase {

    def initRoot = "D:\\My Music\\iTunes\\iTunes Media\\Music"

    void testGetRootDir() {
        assert initRoot == new Config().rootDir
    }

    void testSetRootDir() {
        assert initRoot == new Config().rootDir
        def c = new Config()
        c.rootDir = "doofus"
        assert initRoot != c.rootDir
        assert "doofus" == c.rootDir
        assert initRoot == new Config().rootDir
    }
}
