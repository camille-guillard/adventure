package adventure.models.adventurer;

import java.util.ArrayList;
import java.util.List;

import adventure.enums.CommandEnum;
import adventure.models.adventurer.behavior.IDirection;
import adventure.models.collect.Treasure;
import adventure.models.worldmap.WorldBox;

public class Adventurer {
	
	private String name;
	private Position position;
	private IDirection direction;
	private List<CommandEnum> commands;
	private List<Treasure> treasures;
	
	public Adventurer(String name, Integer x, Integer y, IDirection direction, List<CommandEnum> commands) {
		this.name = name;
		position = new Position(x, y);
		this.direction = direction;
		this.commands = commands;
		this.treasures = new ArrayList<>();
	}

	public Position movingForward() {
		return this.direction.movingForward(this.position);
	}

	public void turnRight() {
		this.direction = this.direction.turnRight();
	}

	public void turnLeft() {
		this.direction = this.direction.turnLeft();
	} 
	
	public Boolean searchTreasure(WorldBox box) {
		Boolean b = false;
		if(box != null && box.getTreasures() != null && !box.getTreasures().isEmpty()) {
			treasures.add(box.getTreasures().remove(0));
			b = true;
		}
		return b;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<CommandEnum> getCommands() {
		return commands;
	}

	public void setCommands(List<CommandEnum> commands) {
		this.commands = commands;
	}
	
	public IDirection getDirection() {
		return direction;
	}

	public void setDirection(IDirection direction) {
		this.direction = direction;
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}

	public void setX(int x) {
		if(position != null) position.setX(x);
	}
	
	public Integer getX(){
		if(position != null) 
			return position.getX();
		return null;
	}
	
	public void setY(int y) {
		if(position != null) position.setY(y);
	}
	
	public Integer getY(){
		if(position != null) 
			return position.getY();
		return null;
	}
	
	public Boolean hasFinish() {
		return commands.isEmpty();
	}
	
}
