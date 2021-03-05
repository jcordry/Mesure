package benchs.jcre.dummyBench;

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class DummyApplet extends TemplateApplet {

	/**
	 * 
	 */
	DummyApplet() {
		super();
	}

	/**
	 * @see javacard.framework.Applet#install(byte[], short, byte)
	 */
	public static void install(byte[] bArray, short bOffset, byte bLength) {
		new DummyApplet().register();
	}	    

	/**
	 * @see Test#getTestCases()
	 */
	public TestCase[] getTestCases() {
		return new TestCase[] {
				new TestCase() {
					public void run(byte[] apduBuffer) {
					}
				}
		};
	}

}


