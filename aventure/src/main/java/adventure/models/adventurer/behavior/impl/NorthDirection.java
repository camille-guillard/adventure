package adventure.models.adventurer.behavior.impl;

import adventure.models.adventurer.Position;
import adventure.models.adventurer.behavior.IDirection;

public class NorthDirection implements IDirection {
	
	@Override
	public Position movingForward(Position p) {
		return new Position(p.getX(), p.getY() - 1);
	}

	@Override
	public IDirection turnRight() {
		return new EastDirection();
	}

	@Override
	public IDirection turnLeft() {
		return new WestDirection();
	}

	@Override
	public String toString() {
		return "NORD";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	} 
}
