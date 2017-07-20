package it.polito.neo4j.manager;

import java.util.Set;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import it.polito.neo4j.jaxb.Graph;
import it.polito.neo4j.jaxb.Graphs;
import it.polito.neo4j.jaxb.Neighbour;
import it.polito.neo4j.jaxb.Paths;
import it.polito.neo4j.exceptions.DuplicateNodeException;
import it.polito.neo4j.exceptions.MyInvalidIdException;
import it.polito.neo4j.exceptions.MyInvalidObjectException;
import it.polito.neo4j.exceptions.MyNotFoundException;

public interface Neo4jDBInteraction {
    public enum NodeType implements Label
    {
        Nffg, Node, Firewall, EndHost, EndPoint, Antispam, Cache, DPI, Mailclient, Mailserver, NAT, VPNAccess, VPNExit, Webclient, Webserver, Configuration, Fieldmodifier;
    }
    public enum RelationType implements RelationshipType
    {
        PathRelationship, OwnerRelationship, ConfigurationRelantionship, ElementRelationship;
    }

    public void createGraphs(Graphs graphs) throws MyNotFoundException, MyInvalidIdException;
    public Graph createGraph(Graph graph) throws MyNotFoundException, MyInvalidIdException;
    public it.polito.neo4j.jaxb.Node createNode(it.polito.neo4j.jaxb.Node node, long graphId) throws MyNotFoundException, DuplicateNodeException, MyInvalidIdException;
    public Neighbour createNeighbour(Neighbour neighbour, long graphId, long nodeId) throws MyNotFoundException;

    public Graphs getGraphs();
    public Graph getGraph(long id) throws MyNotFoundException;
    public Set<it.polito.neo4j.jaxb.Node> getNodes(long graphId) throws MyNotFoundException;
    public it.polito.neo4j.jaxb.Node getNode(long graphId, long nodeId) throws MyNotFoundException;
    public Set<Neighbour> getNeighbours(long graphId, long nodeId) throws MyNotFoundException;
    public Neighbour getNeighbour(long graphId, long nodeId, long neighbourId) throws MyNotFoundException;

    public void deleteGraph(long id) throws MyNotFoundException;
    public void deleteNode(long graphId, long nodeId) throws MyNotFoundException;
    public void deleteNeighbour(long graphId, long nodeId, long neighbourId) throws MyNotFoundException;

    public it.polito.neo4j.jaxb.Node updateNode(it.polito.neo4j.jaxb.Node node, long graphId, long nodeId) throws MyNotFoundException, MyInvalidObjectException, MyInvalidIdException;
    public Graph updateGraph(Graph graph, long graphId) throws MyNotFoundException, MyInvalidObjectException, DuplicateNodeException, MyInvalidIdException;
    public it.polito.neo4j.jaxb.Node updateNeighbour(Neighbour neighbour, long graphId, long nodeId, long neighbourId) throws MyNotFoundException, MyInvalidObjectException;

    public Paths findAllPathsBetweenTwoNodes(long graphId, String srcName, String dstName, String direction) throws MyNotFoundException;
}
