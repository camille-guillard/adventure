package adventure.models.adventurer;

import java.util.List;

import adventure.enums.CommandEnum;
import adventure.models.adventurer.behavior.IDirection;
import adventure.models.worldmap.WorldMap;

public class Player {
	private String name;
	private Position position;
	private IDirection direction;
	private List<CommandEnum> commands;
	
	public Player(String name, Integer x, Integer y, IDirection direction, List<CommandEnum> commands) {
		this.name = name;
		this.position = new Position(x, y);
		this.direction = direction;
		this.commands = commands;
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
	
	public String action(WorldMap worldMap) {
		return null;
	};
	
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
