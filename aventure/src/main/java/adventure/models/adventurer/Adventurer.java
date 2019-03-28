package adventure.models.adventurer;

import java.util.ArrayList;
import java.util.List;

import adventure.enums.CommandEnum;
import adventure.models.adventurer.behavior.IDirection;
import adventure.models.collect.Treasure;
import adventure.models.worldmap.WorldBox;
import adventure.models.worldmap.WorldMap;

public class Adventurer extends Player {
	
	private List<Treasure> treasures;
	
	public Adventurer(String name, Integer x, Integer y, IDirection direction, List<CommandEnum> commands) {
		super(name, x, y, direction, commands);
		this.treasures = new ArrayList<>();
	}
	
	public Boolean searchTreasure(WorldBox box) {
		Boolean b = false;
		if(box != null && box.getTreasures() != null && !box.getTreasures().isEmpty()) {
			treasures.add(box.getTreasures().remove(0));
			b = true;
		}
		return b;
	}
	
	@Override
	public String action(WorldMap worldMap) {
		WorldBox box = worldMap.getWorldBox(getPosition());
		if(searchTreasure(box)) {
			return getName()+" a trouvé un trésor!";
		} 
		return null;
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}
	
}
