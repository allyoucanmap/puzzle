package primitives;

import java.util.List;

public class RelationOSM extends Primitive{
	
	private List<MemberOSM> members;
	
	public RelationOSM(String[] attributes, List<TagOSM> tags, List<MemberOSM> members){
		for(String a : attributes){
			super.attributes.add(a);
		}
		super.tags = tags;
		this.members = members;
	}
	
	public List<MemberOSM> getMembers() {
		return members;
	}

	@Override
	public String toString() {
		String text = ""; for(String a: attributes){ text += a + " ";} 
		text += " MEMBER " + members.size() + " "; 
		text += " TAG " + tags.size() + " "; for(TagOSM a: tags){ text += a.getK() + " ";}
		return text;
	}
	
}
