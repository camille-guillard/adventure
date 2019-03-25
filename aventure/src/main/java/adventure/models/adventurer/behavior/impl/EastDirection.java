package adventure.models.adventurer.behavior.impl;

import adventure.models.adventurer.Position;
import adventure.models.adventurer.behavior.IDirection;

public class EastDirection implements IDirection {
	
	@Override
	public Position movingForward(Position p) {
		return new Position(p.getX() + 1, p.getY());
	}

	@Override
	public IDirection turnRight() {
		return new SouthDirection();
	}

	@Override
	public IDirection turnLeft() {
		return new NorthDirection();
	}
	
	@Override
	public String toString() {
		return "EST";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	}
}
