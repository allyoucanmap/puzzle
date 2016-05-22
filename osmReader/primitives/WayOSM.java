package primitives;

import java.util.List;

public class WayOSM extends Primitive{
	
	private List<NdOSM> nds;
	
	public WayOSM(String[] attributes, List<TagOSM> tags, List<NdOSM> nds){
		for(String a : attributes){
			super.attributes.add(a);
		}
		super.tags = tags;
		this.nds = nds;
	}
	
	public List<NdOSM> getNds() {
		return nds;
	}
	
	@Override
	public String toString() {
		String text = ""; for(String a: attributes){ text += a + " ";} 
		text += " TAG "; for(TagOSM a: tags){ text += a.getK() + " ";}
		text += " ND "; for(NdOSM a: nds){ text += a.getRef() + " ";}
		return text;
	}
	
}
