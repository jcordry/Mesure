package scripts.templates;

public class MultiTestCase extends TestCase {
	short innerLoopIterCount = 1;
	
	  /**
	   * Builds a new test case with the specified parameter.
	   * Use conventional TestCase parameters.
	   * @param <b>innerLoopIterCount</b> the inner loop iteration count
	   * @param <b>divisor</b> delay result should be divided by this value to get correct value
	   */
	 	public MultiTestCase(String name, int id, int x, int y, short innerLoopIterCount) {
			super(name, id, x, y);
			this.innerLoopIterCount = innerLoopIterCount;
		}

	public short getInnerLoopIterCount() {
		return innerLoopIterCount;
	}

}
