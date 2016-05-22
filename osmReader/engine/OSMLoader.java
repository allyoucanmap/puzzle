package engine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.OSMData;
import primitives.Location;
import primitives.MemberOSM;
import primitives.NdOSM;
import primitives.NodeOSM;
import primitives.Primitive;
import primitives.RelationOSM;
import primitives.TagOSM;
import primitives.WayOSM;

public class OSMLoader {

	public static OSMData loadByURL(String url){
		
		Document doc = loadXML(url);
		NodeList nodes = doc.getElementsByTagName("node");
		NodeList ways = doc.getElementsByTagName("way");
		NodeList relations = doc.getElementsByTagName("relation");
		
		OSMData data = new OSMData();
		
		getPrimitives( nodes, Primitive.ATTRIBUTES, data, "nodes" );
		getPrimitives( ways, Primitive.ATTRIBUTES, data, "ways" );
		getPrimitives( relations, Primitive.ATTRIBUTES, data, "relations" );
		
		return data;
	}
	
	private static void getPrimitives( NodeList list, String[] attributes, OSMData data, String type ){
		
		for(int i = 0; i < list.getLength(); i++){
			
			String[] current_attributes = new String[attributes.length];
			List<TagOSM> tagsOSM = new ArrayList<TagOSM>();
			List<NdOSM> ndsOSM = new ArrayList<NdOSM>();
			List<MemberOSM> membersOSM = new ArrayList<MemberOSM>();
			
			Node n = list.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE)
                continue;
            Element e = (Element) n;
            
            for(int j = 0; j < attributes.length; j++){
            	current_attributes[j] = e.getAttribute(attributes[j]);
            }
            
            NodeList tags = e.getElementsByTagName("tag");
           
            for(int j = 0; j < tags.getLength(); j++){
            	Node _n = tags.item(j);
            	if (_n.getNodeType() != Node.ELEMENT_NODE)
            		continue;
            	Element _e = (Element) _n;
            	tagsOSM.add(new TagOSM(_e.getAttribute("k"), _e.getAttribute("v")));
            }
            
            switch (type) {
            	case "nodes":  data.addNode(new NodeOSM(current_attributes, tagsOSM,new Location(e.getAttribute("lat"),e.getAttribute("lon"))));
            	break;
            	case "ways": 
            		NodeList nds = e.getElementsByTagName("nd");
            			for(int j = 0; j < nds.getLength(); j++){
            				Node _n = nds.item(j);
            				if (_n.getNodeType() != Node.ELEMENT_NODE)
            					continue;
            				Element _e = (Element) _n;
            				ndsOSM.add(new NdOSM(_e.getAttribute("ref")));
            		};
            		data.addWay(new WayOSM(current_attributes, tagsOSM, ndsOSM));
            	break;
            	case "relations":
            		NodeList members = e.getElementsByTagName("member");
        			for(int j = 0; j < members.getLength(); j++){
        				Node _n = members.item(j);
        				if (_n.getNodeType() != Node.ELEMENT_NODE)
        					continue;
        				Element _e = (Element) _n;
        				membersOSM.add(new MemberOSM(_e.getAttribute("type"),_e.getAttribute("ref"),_e.getAttribute("role")));
        			};
        			data.addRelation(new RelationOSM(current_attributes, tagsOSM, membersOSM));
            	break;
            	default:;
            	break;
            }
		}
	}
	
	private static Document loadXML(String url){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			return factory.newDocumentBuilder().parse(new URL(url).openStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
    }
	
}
