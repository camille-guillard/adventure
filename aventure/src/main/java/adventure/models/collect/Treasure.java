package adventure.models.collect;

import adventure.models.adventurer.Position;

public class Treasure {
	private Position initialPosition;
	
	public Treasure(Integer x, Integer y) {
		this.initialPosition = new Position(x, y);
	}

	public Position getInitialPosition() {
		return initialPosition;
	}
	
}
