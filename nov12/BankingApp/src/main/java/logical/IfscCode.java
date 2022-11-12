package logical;

public enum IfscCode {
	SINGAMPUNARI("SMPI000314"),
	PONNAMARAVATHI("PMVI000314"),
	KARAIKUDI("KKDI000314"),
	SIVAGANGAI("SVGI000314");
	
	private final String code;
	
	IfscCode(String code){
		this.code=code;
	}
	
	public String toString() {
		return code;
	}

}
