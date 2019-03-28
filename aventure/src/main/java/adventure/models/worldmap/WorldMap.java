package adventure.models.worldmap;

import java.util.ArrayList;
import java.util.List;

import adventure.enums.CaseTypeEnum;
import adventure.models.adventurer.Player;
import adventure.models.adventurer.Position;

public class WorldMap {
	
	private Integer width;
	private Integer height;
		
	private WorldBox[][] world;
	
	List<Player> players = new ArrayList<>();
	
	public WorldMap(int width, int height) {
		this.width = width;
		this.height = height;
		initWorldMap(width, height);
	}
	
	public void initWorldMap(int width, int height) {
		world = new WorldBox[width][height];
		for(int i=0; i < world.length; i++) {
			for(int j=0; j < world[i].length; j++) {
				world[i][j] = new WorldBox(CaseTypeEnum.PLAINE);
			}
		}
	}
	
	public WorldBox getWorldBox(Integer x, Integer y) {
		if(world != null && x >= 0 && y >= 0 && x < width && y < height) {
			return world[x][y];
		} else {
			return null;
		}
	}
	
	public WorldBox getWorldBox(Position p) {
		return this.getWorldBox(p.getX(), p.getY());
	}	

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public WorldBox[][] getWorld() {
		return world;
	}

	public void setWorld(WorldBox[][] world) {
		this.world = world;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
		validatePlayers();
	}

	private void validatePlayers() {
		if(world != null) {
			for(Player player : players) {
				try {
					world[player.getX()][player.getY()].setOccupied(true);
				} catch (ArrayIndexOutOfBoundsException e) {
					players.remove(player);
				}
			}
		}
	}
	
	public Boolean hasFinish() {
		for(Player player : players) {
			if(!player.hasFinish()) {
				return false;
			}
		}
		return true;
	}
	
}
