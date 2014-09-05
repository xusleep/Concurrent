package zhonglin.test.chapter8.movingbox.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MovingMap {
	private final PlaceInterface[][] placeArray;
	private final List<Position> road = new LinkedList<Position>();
	private final int rowCount = 10;
	private final int rowStartY = 0;
	private final int rowIncrement = 1; 
	private final int columnCount = 10;
	private final int columnStartX = 0;
	private final int columnIncrement = 1;
	
	public MovingMap()
	{
		
		placeArray = new PlaceInterface[rowCount][columnCount];
		for(int i = 0; i < rowCount; i++){
			for(int j = 0; j < columnCount; j++)
			{
				placeArray[i][j] = new Place(new Position(columnStartX + columnIncrement * j, rowStartY + i * rowIncrement));
				if(i == 0)
				{
					if( j != 0)
					{
						placeArray[i][j - 1].setRight(placeArray[i][j]);
						placeArray[i][j].setLeft(placeArray[i][j - 1]);
					}
				}
				if(j == 0)
				{
					if(i != 0)
					{
						placeArray[i - 1][j].setUp(placeArray[i][j]);
						placeArray[i][j].setDown(placeArray[i - 1][j]);
					}
				}
				if(i != 0 && j != 0)
				{
					placeArray[i - 1][j].setUp(placeArray[i][j]);
					placeArray[i][j].setDown(placeArray[i - 1][j]);
					placeArray[i][j - 1].setRight(placeArray[i][j]);
					placeArray[i][j].setLeft(placeArray[i][j - 1]);
				}
			}	
		}
		List<PlaceInterface> list = new LinkedList<PlaceInterface>();
		createRoad(placeArray[0][0], list);
	}
	
	public boolean createRoad(PlaceInterface p, List<PlaceInterface> list)
	{
		List<Place> nei = p.getNeighbor();
		if(nei.size() == 0)
		{
			return true;
		}
		if(p.getUp() != null){
			list.add(p);
			return createRoad(p.getUp(), list);
		}
		return false;
	}
	
	
	public boolean containsInRoad(Position p)
	{
		for(int i = 0; i < this.road.size(); i++)
		{
			if(p.getX() == this.road.get(i).getX() && p.getY() == this.road.get(i).getY())
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	public void printMoveMap()
	{
		for(int i = rowCount - 1; i >= 0 ; i--){
			for(int j = 0; j < columnCount; j++)
			{
				System.out.print(placeArray[i][j].toString() + "");
			}	
			System.out.println();
		}
	}
	
	public static void  main(String args[]){
		MovingMap map = new MovingMap();
		map.printMoveMap();
	}
}
