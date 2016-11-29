package mavenTest;

import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import mavenSource.*;

public class UtilTest extends TestCase {
	Util util = new Util();

	public void testErstesHalbjahr() {
		// assertEquals(true, Util.istErstesHalbjahr(1));
		for (int i = 1; i < 13; i++) {
			if (i < 8) {
				assertEquals(true, Util.istErstesHalbjahr(i));
			} else {
				assertEquals(false, Util.istErstesHalbjahr(i));
			}
		}
	}

}
