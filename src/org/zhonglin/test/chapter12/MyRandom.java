package zhonglin.test.chapter12;

public class MyRandom {
	
	public static int xorshift(int y){
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(xorshift(10));
	}

}
