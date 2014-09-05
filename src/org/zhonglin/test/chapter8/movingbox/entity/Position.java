package zhonglin.test.chapter8.movingbox.entity;

public class Position {
	private final int x;
	private final int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return (((Position)arg0).x == this.x && ((Position)arg0).y == this.y);
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
	
	
}
