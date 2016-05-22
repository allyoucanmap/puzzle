package primitives;

import java.util.ArrayList;
import java.util.List;

public class Primitive {
	
	public static final String[] ATTRIBUTES = new String[]{"id", "visible", "version", "changeset", "timestamp", "user", "uid"};
	protected List<String> attributes = new ArrayList<String>();
	protected List<TagOSM> tags;
	
	public String getId() {
		return attributes.get(0);
	}

	public String getVisible() {
		return attributes.get(1);
	}

	public String getVersion() {
		return attributes.get(2);
	}

	public String getChangeset() {
		return attributes.get(3);
	}

	public String getTimestamp() {
		return attributes.get(4);
	}

	public String getUser() {
		return attributes.get(5);
	}

	public String getUid() {
		return attributes.get(6);
	}
	
	public List<TagOSM> getTags() {
		return tags;
	}
	
	public boolean hasTag(String tag){
		for(TagOSM t: tags){
			if(t.getK().equals(tag)){
				return true;
			}
		}
		return false;
	}

}	
