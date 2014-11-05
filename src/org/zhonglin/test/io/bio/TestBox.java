package zhonglin.test.io.bio;

/***
 * 自动装箱 与拆箱问题研究
 * @author zhonxu
 *
 */
public class TestBox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer a = 23;
		Integer b = 23;
		Integer c = 46;
		Long g = 46l;
		System.out.println(a == b);
		System.out.println(a.equals(b));
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
	}

}
