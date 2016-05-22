package data;

import java.util.ArrayList;
import java.util.List;

import elements.Poly;
import primitives.MemberOSM;
import primitives.NdOSM;
import primitives.NodeOSM;
import primitives.RelationOSM;
import primitives.WayOSM;

public class OSMData {
	
	private List<NodeOSM> nodes = new ArrayList<NodeOSM>();
	private List<WayOSM> ways = new ArrayList<WayOSM>();
	private List<RelationOSM> relations = new ArrayList<RelationOSM>();
	
	public void addNode(NodeOSM node){
		this.nodes.add(node);
	}
	
	public void addWay(WayOSM way){
		this.ways.add(way);
	}
	
	public void addRelation(RelationOSM relation){
		this.relations.add(relation);
	}
	
	public List<Poly> getByTag(String tag){
		List<Poly> poly = new ArrayList<Poly>();
		for(WayOSM w: ways){
			if(w.hasTag(tag)){
				List<NodeOSM> ns = getNodesFromWay(w);
				RelationOSM rel = hasRelation(w);
				poly.add(new Poly("building", w.getTags(), rel, ns));
			}
		}
		return poly;
	}
	
	private RelationOSM hasRelation(WayOSM way){
		for(RelationOSM r: relations){
			for(MemberOSM m: r.getMembers()){
				if(m.getRef().equals(way.getId())){
					return r;
				}
			}
		}
		return null;
	}
	
	private List<NodeOSM> getNodesFromWay(WayOSM way){
		List<NodeOSM> ns = new ArrayList<NodeOSM>();
		for(NdOSM nd: way.getNds()){
			for(NodeOSM n: nodes){
				if(n.getId().equals(nd.getRef())){
					ns.add(n);
					break;
				}
			}
		}
		return ns;
	}

	public List<NodeOSM> getNodes() {
		return nodes;
	}

	public List<WayOSM> getWays() {
		return ways;
	}

	public List<RelationOSM> getRelations() {
		return relations;
	}

}
