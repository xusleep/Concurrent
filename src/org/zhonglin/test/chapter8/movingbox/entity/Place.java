package zhonglin.test.chapter8.movingbox.entity;

import java.util.LinkedList;
import java.util.List;

public class Place implements PlaceInterface {
	
	private EntityInterface entity;
	private PlaceInterface up;
	private PlaceInterface down;
	private PlaceInterface left;
	private PlaceInterface right;
	private Position position;
	
	public Place(Position position){
		this.setPosition(position);
	}

	@Override
	public PlaceInterface getUp() {
		// TODO Auto-generated method stub
		return up;
	}

	@Override
	public void setUp(PlaceInterface up) {
		// TODO Auto-generated method stub
		this.up = up;
	}

	@Override
	public PlaceInterface getDown() {
		// TODO Auto-generated method stub
		return this.down;
	}

	@Override
	public void setDown(PlaceInterface down) {
		// TODO Auto-generated method stub
		this.down = down;
	}

	@Override
	public PlaceInterface getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public void setRight(PlaceInterface right) {
		// TODO Auto-generated method stub
		this.right = right;
	}

	@Override
	public PlaceInterface getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public void setLeft(PlaceInterface left) {
		// TODO Auto-generated method stub
		this.left = left;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		this.position = position;
	}

	@Override
	public void placeEntity(EntityInterface entity) {
		// TODO Auto-generated method stub
		this.entity = entity;
	}

	@Override
	public EntityInterface removeEntity() {
		// TODO Auto-generated method stub
		return this.entity;
	}
	
	public boolean hasEntity()
	{
		return this.entity != null;
	}
	
	public 	List<Place> getNeibough()
	{
		List<Place> list = new LinkedList<Place>();
		if(this.left != null)
			list.add((Place) this.left);
		if(this.right != null)
			list.add((Place) this.right);
		if(this.up != null)
			list.add((Place) this.up);
		if(this.down != null)
			list.add((Place) this.down);
		return list;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + this.getPosition().toString() + "" 
				+ (this.hasEntity() ? ",*]":",R]");
	}

}
