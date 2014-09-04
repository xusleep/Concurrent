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
		createRoad();
	}
	
	public void createRoad()
	{
		int k = 100;
		Random getTo = new Random();
		while(k > 0)
		{
			k--;
			int count = 100;
			this.road.clear();
			int i = 0; 
			int j = 0;
			while(true){
				int tmpi = i;
				int tmpj = j;
				if ( i == 9 && j == 9)
					break;
				List<Place> ps = this.placeArray[i][j].getNeibough();
				int p = getTo.nextInt(ps.size());
				if(p == 0)
				{
					if(i == 9)
						continue;
					i++;
				}
				else if(p == 3)
				{
					if(i == 0)
						continue;
					i--;
				}
				else if(p == 1)
				{
					if(j == 9)
						continue;
					j++;
				}
				else if(p == 2)
				{
					if(j == 0)
						continue;
					j--;
				}
				Position newP = new Position(i, j);
				System.out.println(newP);
				if(this.containsInRoad(newP))
				{
					i = tmpi;
					j = tmpj;
					if(count-- <= 0)
						break;
					continue;
				}
				placeArray[i][j].placeEntity(new BlockEntity());
				this.road.add(newP);
			}
			if(count >= 0 )
				break;
		}
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
