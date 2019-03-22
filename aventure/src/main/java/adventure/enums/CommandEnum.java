package adventure.enums;

public enum CommandEnum {
	
	AVANCER(1,"A"),
	TOURNER_DROITE(2, "D"),
	TOURNER_GAUCHE(3, "G");
	
	private int id;
	private String command;
	
	private CommandEnum (final int id, final String command) {
		this.id = id;
		this.command = command;
	}
	
	public static CommandEnum getEnumByCommand(String command) {
		for (CommandEnum lEnum : values()) {
			if (lEnum.getCommand().equals(command)) {
				return lEnum;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	public String getCommand() {
		return command;
	}
	
}
