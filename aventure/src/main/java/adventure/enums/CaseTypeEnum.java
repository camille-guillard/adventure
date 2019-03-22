package adventure.enums;

public enum CaseTypeEnum {
	
	PLAINE(1,"."),
	MONTAGNE(2, "M");
	
	private int id;
	private String logo;
	
	private CaseTypeEnum (final int id, final String logo) {
		this.id = id;
		this.logo = logo;
	}
	
	public static CaseTypeEnum getEnumWithId(int pId) {
		for (CaseTypeEnum lEnum : values()) {
			if (lEnum.getId() == pId) {
				return lEnum;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	public String getLogo() {
		return logo;
	}
	
}
