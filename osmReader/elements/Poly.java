package elements;

import java.util.List;

import primitives.NodeOSM;
import primitives.RelationOSM;
import primitives.TagOSM;

public class Poly{
	
	private List<TagOSM> tags;
	private RelationOSM relation;
	
	private String name;
	private double[] coord_lon;
	private double[] coord_lat;
	
	public Poly( String name, List<TagOSM> tags, RelationOSM relation, List<NodeOSM> nodes ){
		this.name = name;
		this.tags = tags;
		this.relation = relation;
		this.coord_lon = new double[nodes.size()];
		this.coord_lat = new double[nodes.size()];
		
		for( int i = 0; i < nodes.size(); i++ ){
			this.coord_lon[i] = nodes.get(i).getCoord().getLon();
			this.coord_lat[i] = nodes.get(i).getCoord().getLat();
		}
	}

	public double[] getCoordLon() {
		return coord_lon;
	}

	public double[] getCoordLat() {
		return coord_lat;
	}

	public List<TagOSM> getTags() {
		return tags;
	}

	public String getName() {
		return name;
	}

	public RelationOSM getRelation() {
		return relation;
	}
	
}
