package adventure.models.worldmap;

import java.util.ArrayList;
import java.util.List;

import adventure.enums.CaseTypeEnum;
import adventure.models.adventurer.Adventurer;
import adventure.models.adventurer.Position;

public class WorldMap {
	
	private Integer width;
	private Integer height;
		
	private WorldBox[][] world;
	
	List<Adventurer> adventurers = new ArrayList<>();
	
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

	public List<Adventurer> getAdventurers() {
		return adventurers;
	}

	public void setAdventurers(List<Adventurer> adventurers) {
		this.adventurers = adventurers;
		validateAdventurers();
	}
	
	private void validateAdventurers() {
		if(world != null) {
			for(Adventurer adventurer : adventurers) {
				try {
					world[adventurer.getX()][adventurer.getY()].setOccupied(true);
				} catch (ArrayIndexOutOfBoundsException e) {
					adventurers.remove(adventurer);
				}
			}
		}
	}
	
	public Boolean hasFinish() {
		for(Adventurer adventurer : adventurers) {
			if(!adventurer.hasFinish()) {
				return false;
			}
		}
		return true;
	}
	
}
