package primitives;

import java.util.List;

public class NodeOSM extends Primitive{

	private Location coord;
	
	public NodeOSM( String[] attributes, List<TagOSM> tags, Location coord){
		for(String a : attributes){
			super.attributes.add(a);
		}
		super.tags = tags;
		this.coord = coord;
	}
	
	public Location getCoord() {
		return coord;
	}
	
	@Override
	public String toString() {
		String text = ""; for(String a: attributes){ text += a + " ";} 
		text += " TAG "; for(TagOSM a: tags){ text += a.getK() + " ";}
		return text;
	}
}
