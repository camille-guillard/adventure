package adventure.models.worldmap;

import java.util.ArrayList;
import java.util.List;

import adventure.enums.CaseTypeEnum;
import adventure.models.adventurer.Position;
import adventure.models.collect.Treasure;

public class WorldBox {
	private Position position;
	private Boolean occupied;
	private CaseTypeEnum caseType;
	private List<Treasure> treasures;
	
	public WorldBox(Integer x, Integer y, CaseTypeEnum caseType) {
		this.position = new Position(x, y);
		this.occupied = false;
		this.caseType = caseType;
		treasures = new ArrayList<>();
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setTreasures(List<Treasure> treasures) {
		this.treasures = treasures;
	}

	public Boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public CaseTypeEnum getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseTypeEnum caseType) {
		this.caseType = caseType;
	}

	public Position getPosition() {
		return position;
	}

}
