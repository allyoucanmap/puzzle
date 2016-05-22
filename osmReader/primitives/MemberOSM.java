package primitives;

public class MemberOSM {
	
	private String type;
	private String ref;
	private String role;
	
	public MemberOSM(String type, String ref, String role){
		this.type = type;
		this.ref = ref;
		this.role = role;
	}
	
	public String getType() {
		return type;
	}
	
	public String getRef() {
		return ref;
	}
	
	public String getRole() {
		return role;
	}

}
