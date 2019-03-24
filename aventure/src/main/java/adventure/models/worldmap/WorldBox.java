package adventure.models.worldmap;

import java.util.ArrayList;
import java.util.List;

import adventure.enums.CaseTypeEnum;
import adventure.models.collect.Treasure;

public class WorldBox {
	private Boolean occupied;
	private CaseTypeEnum caseType;
	private List<Treasure> treasures;
	
	public WorldBox(CaseTypeEnum caseType) {
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

	public Boolean getOccupied() {
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
		
}
