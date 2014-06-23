package data.database.connection;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import data.City;
import data.Entity;
import data.RelTypes;

public class WikiNerGraphConnector {

	private GraphDatabaseService graphDb;
	
	public WikiNerGraphConnector() {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("./database/");
	}

	public Node createCity(City city) {
		Transaction tx = graphDb.beginTx();	
		Node rc;
		Label myLabel = DynamicLabel.label("City");
		
		rc = graphDb.createNode();
		rc.addLabel(myLabel);
		rc.setProperty("name", city.getName());
		rc.setProperty("latitude", city.getLati());
		rc.setProperty("longitude", city.getLongi());
		
		tx.success();
		return rc;
	}

	public Node createEntity(Entity entity) {
		Transaction tx = graphDb.beginTx();	
		Node rc;
		Label myLabel = DynamicLabel.label("Entity");
		
		rc = graphDb.createNode();
		rc.addLabel(myLabel);
		rc.setProperty("name", entity.getName());
		rc.setProperty("type", entity.getType());
		rc.setProperty("idf", entity.getIdf());
		
		tx.success();
		return rc;
	}

	public void createConnection(Node city, Node entity, int count) {
		Transaction tx = graphDb.beginTx();	
		Relationship relationship;
		
		relationship = entity.createRelationshipTo(city, RelTypes.IN);
		relationship.setProperty("count", count);
		
		tx.success();
	}
	
	

}
