package test;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import data.RelTypes;

public class GraphTest {

	GraphDatabaseService graphDb;
	Node firstNode;
	Node secondNode;
	Relationship relationship;
	
	public GraphTest() {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("./database/");
		Transaction tx = graphDb.beginTx();		
		Label label = DynamicLabel.label( "message" );
		
//		firstNode = graphDb.createNode();
//		firstNode.setProperty( "message", "Hello, " );
//		secondNode = graphDb.createNode();
//		secondNode.setProperty( "message", "World!" );
//
//		relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
//		relationship.setProperty( "message", "brave Neo4j " );
//		
//		System.out.print( firstNode.getProperty( "message" ) );
//		System.out.print( relationship.getProperty( "message" ) );
//		System.out.print( secondNode.getProperty( "message" ) );
//				
//		firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
//		firstNode.delete();
//		secondNode.delete();
		
		ResourceIterator<Node> users = graphDb
	            .findNodesByLabelAndProperty( label, "message", "Hello, " )
	            .iterator();
		
		Node firstUserNode;
	    if ( users.hasNext() )
	    {
	        firstUserNode = users.next();
	        System.out.println("test: " + firstUserNode.getProperty("message"));
	    }
	    users.close();
	    
		tx.success();
		graphDb.shutdown();
	}

	public static void main(String[] args) {
		new GraphTest();
	}

}
