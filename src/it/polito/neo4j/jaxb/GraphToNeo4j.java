package it.polito.neo4j.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

import it.polito.neo4j.jaxb.Graph;
import it.polito.neo4j.jaxb.Node;
import it.polito.verigraph.model.Configuration;
import it.polito.neo4j.jaxb.Graphs;

public class GraphToNeo4j {	
	public static it.polito.neo4j.jaxb.Graph generateObject(it.polito.verigraph.model.Graph gr) throws JsonParseException, JsonMappingException, IOException {
		it.polito.neo4j.jaxb.Graph graph;
		graph=(new ObjectFactory()).createGraph();
		graph.setId(gr.getId());		
		
		List<it.polito.neo4j.jaxb.Node> nodes=new ArrayList<it.polito.neo4j.jaxb.Node>();		
		for(Map.Entry<Long, it.polito.verigraph.model.Node> c : gr.getNodes().entrySet()){
			it.polito.neo4j.jaxb.Node node=(new ObjectFactory()).createNode();
			node.setId(c.getValue().getId());
			node.setName(c.getValue().getName());			
			node.setFunctionalType(FunctionalTypes.fromValue(c.getValue().getFunctional_type().toUpperCase()));
			List<Neighbour> neighbours=new ArrayList<Neighbour>();
			
			/* setNeighbours*/
			for(Map.Entry<Long, it.polito.verigraph.model.Neighbour> a : c.getValue().getNeighbours().entrySet()){
				Neighbour neighbour=(new ObjectFactory()).createNeighbour();				
				neighbour.setId(a.getValue().getId());
				neighbour.setName(a.getValue().getName());
				neighbours.add(neighbour);		
		}
			node.getNeighbour().addAll(neighbours);
			
			/* setConfigurations */			
			it.polito.neo4j.jaxb.Configuration configuration=(new ObjectFactory()).createConfiguration();
			JsonNode json=c.getValue().getConfiguration().getConfiguration();
			setConfiguration(configuration, c.getValue(), json);
			node.setConfiguration(configuration);
			nodes.add(node);
	}
		graph.getNode().addAll(nodes);
		return graph;
	}

	public static it.polito.neo4j.jaxb.Configuration ConfToNeo4j(it.polito.verigraph.model.Configuration nodeConfiguration, it.polito.verigraph.model.Node node) throws JsonParseException, JsonMappingException, IOException {
		it.polito.neo4j.jaxb.Configuration configuration=(new ObjectFactory()).createConfiguration();
		JsonNode nodes=nodeConfiguration.getConfiguration();	
		setConfiguration(configuration, node, nodes);
		
		return configuration;
		
		
	}
	
@SuppressWarnings("unchecked")
private static void setConfiguration(it.polito.neo4j.jaxb.Configuration configuration, it.polito.verigraph.model.Node node, JsonNode nodes) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
	//JsonNode nodes=node.getConfiguration().getConfiguration();	
	String empty="[]";
	
	switch(node.getFunctional_type().toUpperCase()){
	case "FIREWALL":{
		configuration.setName(node.getFunctional_type().toLowerCase());
		Firewall firewall=new Firewall();
		List<Elements> elements_list=new ArrayList<Elements>();
		
		if(!nodes.toString().equals(empty)){	
			ObjectMapper mapper=new ObjectMapper();			
			java.util.Map<String, String> map=new LinkedHashMap();		
			String input;
			Matcher matcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(nodes.toString());
			if(matcher.find()){
				input=matcher.group(1);
			}else
				input=nodes.toString();
			Pattern pattern=Pattern.compile("\\{([^\\}]*)\\}");
			List<String> list = new ArrayList<String>();
			Matcher match= pattern.matcher(input);
			while (match.find()) {
			    list.add(match.group());
			}	
						
			try{
				for(String string : list){
					//System.out.println("stampa list: " + string);
					map.putAll(mapper.readValue(string, LinkedHashMap.class));
					//System.out.println("mappe: "+map);					
				}
				for(java.util.Map.Entry<String, String> m : map.entrySet()){
					Elements e=new Elements();				
					e.setDestination(m.getKey());
					e.setSource(m.getValue());	
					elements_list.add(e);
				}
			
		
			}catch(JsonGenerationException e) {
	
		        e.printStackTrace();
	
		    } catch (JsonMappingException e) {
	
		        e.printStackTrace();
	
		    } catch (IOException e) {
	
		        e.printStackTrace();
	
		    }
		
		
			
		}
			else{
				System.out.println("elements_list of Firewall " +node.getName()+" empty");
				
			}
		firewall.getElements().addAll(elements_list);
		configuration.setFirewall(firewall);
		break;
		
				
		}
			
				
	
	case "ANTISPAM":{
		configuration.setName(node.getFunctional_type().toLowerCase());	
		Antispam antispam=new Antispam();
		List<String> source=new ArrayList<String>();
		
		if(!nodes.toString().equals(empty)){
			ObjectMapper mapper=new ObjectMapper();			
			List<String> list=new ArrayList<String>();
			
			 try {
	
			        list = mapper.readValue(nodes.toString(), ArrayList.class);
			      
			        for(String s : list){
			    		//System.out.println("antispam: " + s);
			    		source.add(s);
			        }
			      
			    } catch (JsonGenerationException e) {
	
			        e.printStackTrace();
	
			    } catch (JsonMappingException e) {
	
			        e.printStackTrace();
	
			    } catch (IOException e) {
	
			        e.printStackTrace();
	
			    }
			
		}
		else{			
			System.out.println("Antispam " +node.getName()+" empty");
		}
		antispam.getSource().addAll(source);
		configuration.setAntispam(antispam);
		break;
	}
	case "CACHE":{
		configuration.setName(node.getFunctional_type().toLowerCase());
		System.out.println("nodes: " + nodes.toString());
		Cache cache=new Cache();
		List<String> resource=new ArrayList<String>();
		
		if(!nodes.toString().equals(empty)){
			ObjectMapper mapper=new ObjectMapper();
			
			List<String> list=new ArrayList<String>();
			 try {
	
			        list = mapper.readValue(nodes.toString(), ArrayList.class);
			      
			        for(String s : list){
			    		resource.add(s);
			        }
			      
			    } catch (JsonGenerationException e) {
	
			        e.printStackTrace();
	
			    } catch (JsonMappingException e) {
	
			        e.printStackTrace();
	
			    } catch (IOException e) {
	
			        e.printStackTrace();
	
			    }			
			
		}
		else{
			System.out.println("Cache " +node.getName()+" empty");			
		}
		cache.getResource().addAll(resource);
		configuration.setCache(cache);
		break;
		
	}
	case "DPI":{
		configuration.setName(node.getFunctional_type().toLowerCase());	
		Dpi dpi=new Dpi();
		List<String> notAllowed=new ArrayList<String>();
		
		if(!nodes.toString().equals(empty)){
			ObjectMapper mapper=new ObjectMapper();
			
			List<String> list=new ArrayList<String>();
			 try {
	
			        list = mapper.readValue(nodes.toString(), ArrayList.class);
			      
			        for(String s : list){			    	
			    		notAllowed.add(s);
			        }
			      
	
			    } catch (JsonGenerationException e) {
	
			        e.printStackTrace();
	
			    } catch (JsonMappingException e) {
	
			        e.printStackTrace();
	
			    } catch (IOException e) {
	
			        e.printStackTrace();
	
			    }
			
			}
		else{
			System.out.println("Dpi " +node.getName()+"  empty");
		}
		dpi.getNotAllowed().addAll(notAllowed);
		configuration.setDpi(dpi);
		break;
	}
	case "ENDHOST":{
		configuration.setName(node.getFunctional_type().toLowerCase());
		Endhost endhost=new Endhost();
		
		if(!nodes.toString().equals(empty)){
			ObjectMapper mapper=new ObjectMapper();		
			java.util.Map<String, String> map=new LinkedHashMap();			
			String input;
			Matcher matcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(nodes.toString());
			if(matcher.find()){
				input=matcher.group(1);
			}else
				input=nodes.toString();
			
			try{
			map=mapper.readValue(input, java.util.LinkedHashMap.class);
			for(java.util.Map.Entry<String, String> m : map.entrySet()){				
				switch(m.getKey()){
				
				case "body":{
					endhost.setBody(m.getValue());
					break;
				}
				case"sequence":{
					endhost.setSequence(new BigInteger(m.getValue()));
					break;
				}
				case "protocol":{
					endhost.setProtocol(ProtocolTypes.fromValue(m.getValue()));
					break;
				}
				case "email_from":{
					endhost.setEmailFrom(m.getValue());
					break;
				}
				case "url":{
					endhost.setUrl(m.getValue());
					break;					
				}
				case "option":{
					endhost.setOptions(m.getValue());
					break;
				}
				case "destination":{
					endhost.setDestination(m.getValue());
					break;
				}
				}
			}
			
			}catch(JsonGenerationException e) {
	
		        e.printStackTrace();
	
		    } catch (JsonMappingException e) {
	
		        e.printStackTrace();
	
		    } catch (IOException e) {
	
		        e.printStackTrace();
	
		    }
		
			
		}
		else{
			System.out.println("Endhost " +node.getName()+" empty");
		}
		configuration.setEndhost(endhost);
		break;
	}
case "ENDPOINT":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Endpoint endpoint=new Endpoint();
	configuration.setEndpoint(endpoint);
	break;
}
case "FIELDMODIFIER":{
	
	configuration.setName(node.getFunctional_type());
	Fieldmodifier fieldmodifier=new  Fieldmodifier();	
	configuration.setFieldmodifier(fieldmodifier);
	break;
	}
	
case "MAILCLIENT":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Mailclient mailclient=new Mailclient();
	
	ObjectMapper mapper=new ObjectMapper();
	System.out.println("nodes: " + nodes.toString());
	java.util.Map<String, String> map=new LinkedHashMap();	
	String input;
	Matcher matcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(nodes.toString());
	if(matcher.find()){
		input=matcher.group(1);
	}else
		input=nodes.toString();
	
	Pattern pattern=Pattern.compile("\\{([^\\}]*)\\}");
	List<String> list = new ArrayList<String>();
	Matcher match= pattern.matcher(input);
	while (match.find()) {
	    list.add(match.group());
	}	
				
	try{
		for(String string : list){
			map.putAll(mapper.readValue(string, LinkedHashMap.class));	
			if(map!=null || !map.isEmpty()){
				for(java.util.Map.Entry<String, String> m : map.entrySet()){				
					switch(m.getKey()){
					case "mailserver":{
						mailclient.setMailserver(m.getValue());
						break;
					}
					default:
						System.out.println("\"mailserver\" object is required");
						break;
					}
				}
			}
		}
	}catch(JsonGenerationException e) {

        e.printStackTrace();

    } catch (JsonMappingException e) {

        e.printStackTrace();

    } catch (IOException e) {

        e.printStackTrace();

    }
	configuration.setMailclient(mailclient);
	break;
}
case "MAILSERVER":{
	
	configuration.setName(node.getFunctional_type().toLowerCase());
	Mailserver mailserver=new Mailserver();
	configuration.setMailserver(mailserver);
	break;
}
case "NAT":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Nat nat=new Nat();
	List<String> source=new ArrayList<String>();
	
	if(!nodes.toString().equals(empty)){
		ObjectMapper mapper=new ObjectMapper();
		List<String> list=new ArrayList<String>();
		 try {
	
		        list = mapper.readValue(nodes.toString(), ArrayList.class);
		    
		        for(String s : list){		    		
		    		source.add(s);
		    	}
		      
		      
	
		    } catch (JsonGenerationException e) {
	
		        e.printStackTrace();
	
		    } catch (JsonMappingException e) {
	
		        e.printStackTrace();
	
		    } catch (IOException e) {
	
		        e.printStackTrace();
	
		    }
		
	}
	else{
		System.out.println("Nat " +node.getName()+" empty");
	}
	nat.getSource().addAll(source);
	configuration.setNat(nat);
	break;
	}
	

case "VPNACCESS":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Vpnaccess vpnaccess=new Vpnaccess();
	ObjectMapper mapper=new ObjectMapper();	
	java.util.Map<String, String> map=new LinkedHashMap();	
	String input;
	Matcher matcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(nodes.toString());
	if(matcher.find()){
		input=matcher.group(1);
	}else
		input=nodes.toString();
	
	Pattern pattern=Pattern.compile("\\{([^\\}]*)\\}");
	List<String> list = new ArrayList<String>();
	Matcher match= pattern.matcher(input);
	while (match.find()) {
	    list.add(match.group());
	}	
				
	try{
		for(String string : list){
			map.putAll(mapper.readValue(string, LinkedHashMap.class));	
			if(map!=null || !map.isEmpty()){
				for(java.util.Map.Entry<String, String> m : map.entrySet()){				
					switch(m.getKey()){
					
					case "vpnexit":{
					vpnaccess.setVpnexit(m.getValue());
					break;
					}
					default:
						System.out.println("\"vpnexit\" is required");
						break;
					}
				}
			}
		}
	}catch(JsonGenerationException e) {

        e.printStackTrace();

    } catch (JsonMappingException e) {

        e.printStackTrace();

    } catch (IOException e) {

        e.printStackTrace();

    }
	configuration.setVpnaccess(vpnaccess);
	break;
}
case "VPNEXIT":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Vpnexit vpnexit=new Vpnexit();
	
	ObjectMapper mapper=new ObjectMapper();	
	java.util.Map<String, String> map=new LinkedHashMap();	
	String input;
	Matcher matcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(nodes.toString());
	if(matcher.find()){
		input=matcher.group(1);
	}else
		input=nodes.toString();
	
	Pattern pattern=Pattern.compile("\\{([^\\}]*)\\}");
	List<String> list = new ArrayList<String>();
	Matcher match= pattern.matcher(input);
	while (match.find()) {
	    list.add(match.group());
	}	
				
	try{
		for(String string : list){
			/* solo 1 stringa presente */
			map.putAll(mapper.readValue(string, LinkedHashMap.class));	
			if(map!=null || !map.isEmpty()){
				for(java.util.Map.Entry<String, String> m : map.entrySet()){
					switch(m.getKey()){
					case "vpnaccess":{
						vpnexit.setVpnaccess(m.getValue());
						break;
					}
					default:{
						System.out.println("\"vpnaccess\" is required");
						break;
					}
					}
				}
			}
		}
	}catch(JsonGenerationException e) {

        e.printStackTrace();

    } catch (JsonMappingException e) {

        e.printStackTrace();

    } catch (IOException e) {

        e.printStackTrace();

    }
	configuration.setVpnexit(vpnexit);
	break;
}
case "WEBCLIENT":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Webclient webclient=new Webclient();
	
	
	ObjectMapper mapper=new ObjectMapper();
	java.util.Map<String, String> map=new LinkedHashMap();
	String input;	
	Matcher matcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(nodes.toString());
	if(matcher.find()){
		input=matcher.group(1);
	}else
		input=nodes.toString();
	
	Pattern pattern=Pattern.compile("\\{([^\\}]*)\\}");
	List<String> list = new ArrayList<String>();
	Matcher match= pattern.matcher(input);
	while (match.find()) {
	    list.add(match.group());
	}	
				
	try{
		for(String string : list){		
			map.putAll(mapper.readValue(string, LinkedHashMap.class));	
			if(map!=null || !map.isEmpty()){
				for(java.util.Map.Entry<String, String> m : map.entrySet()){					
					switch(m.getKey()){
					case "webserver":{
						webclient.setNameWebServer(m.getValue());
						break;
					}
					default:{
						System.out.println("\"webserver\" object is required");
						break;
					}
					}
				}
			}
		}
	}catch(JsonGenerationException e) {

        e.printStackTrace();

    } catch (JsonMappingException e) {

        e.printStackTrace();

    } catch (IOException e) {

        e.printStackTrace();

    }
	configuration.setWebclient(webclient);
	break;
}
case "WEBSERVER":{
	configuration.setName(node.getFunctional_type().toLowerCase());
	Webserver webserver=new Webserver();
	configuration.setWebserver(webserver);
	break;
	}
}
	
	
	
		
}

public static Neighbour NeighbourToNeo4j(it.polito.verigraph.model.Neighbour n){
	Neighbour               neighbourRoot;
	neighbourRoot=(new ObjectFactory()).createNeighbour();
	neighbourRoot.setId(n.getId());
	neighbourRoot.setName(n.getName());
	return neighbourRoot;
	
}

public static Node NodeToNeo4j(it.polito.verigraph.model.Node n) throws JsonParseException, JsonMappingException, IOException{
	Node              nodeRoot;
	it.polito.neo4j.jaxb.Configuration configuration=(new ObjectFactory()).createConfiguration();
	nodeRoot=(new ObjectFactory()).createNode();
	nodeRoot.setId(n.getId());
	nodeRoot.setName(n.getName());
	nodeRoot.setFunctionalType(FunctionalTypes.fromValue(n.getFunctional_type().toUpperCase()));
	for(Map.Entry<Long, it.polito.verigraph.model.Neighbour> neighbour : n.getNeighbours().entrySet()){
		it.polito.verigraph.model.Neighbour neighb=neighbour.getValue();
		nodeRoot.getNeighbour().add(NeighbourToNeo4j(neighb));
	}
	JsonNode json=n.getConfiguration().getConfiguration();
	setConfiguration(configuration, n, json);
	
	nodeRoot.setConfiguration(configuration);
	
	return nodeRoot;
	
}
}

