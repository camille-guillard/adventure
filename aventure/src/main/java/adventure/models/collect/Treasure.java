package adventure.models.collect;

public class Treasure {
	private Integer initialX;
	private Integer initialY;
	
	public Treasure(Integer x, Integer y) {
		this.initialX = x;
		this.initialY = y;
	}

	public Integer getInitialX() {
		return initialX;
	}

	public Integer getInitialY() {
		return initialY;
	}
	
}
