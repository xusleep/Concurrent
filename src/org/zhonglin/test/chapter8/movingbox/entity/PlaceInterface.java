package zhonglin.test.chapter8.movingbox.entity;

import java.util.List;

public interface PlaceInterface {
	
	public void placeEntity(EntityInterface entity);
	public EntityInterface removeEntity();
	
	public PlaceInterface getUp();
	public void setUp(PlaceInterface up);
	
	public PlaceInterface getDown();
	public void setDown(PlaceInterface down);
	
	public PlaceInterface getRight();
	public void setRight(PlaceInterface right);
	
	public PlaceInterface getLeft();
	public void setLeft(PlaceInterface left);
	
	public Position getPosition();
	public void setPosition(Position position);
	
	public 	List<Place> getNeighbor();
}
