package data.database.connection;

import java.util.ArrayList;
import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

import data.City;
import data.DataDump;
import data.Entity;
import data.RelTypes;

public class WikiNerGraphConnector {

	private GraphDatabaseService graphDb;
	private Label cityLabel = DynamicLabel.label("City");
	private Label entityLabel = DynamicLabel.label("Entity");
	private Index<Node> entities;
	private Index<Node> cities;
	private String dbDir = "./database/";
	private Index<Node> test;
	
	public WikiNerGraphConnector() {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbDir);
		IndexDefinition indexDefinition;
		Schema schema;
		Transaction tx = graphDb.beginTx();	
		
		schema = graphDb.schema();
		IndexManager index = graphDb.index();
		entities = index.forNodes(entityLabel.name());
	    //indexDefinition = schema.indexFor( entityLabel ).on( "nameType" ).create();
		cities = index.forNodes(cityLabel.name());
		
		tx.success();
		tx.close();
	}

	public Node createCity(City city) {
		Transaction tx = graphDb.beginTx();	
		Node rc;
		
		rc = graphDb.createNode();
		rc.setProperty("name", city.getName());
		rc.setProperty("latitude", city.getLati());
		rc.setProperty("longitude", city.getLongi());
		rc.addLabel(cityLabel);
		tx.success();
		tx.close();
		return rc;
	}

	public Node createEntity(Entity entity) {
		Transaction tx = graphDb.beginTx();	
		Node rc;
		int counter;
		Node temp = null;
		IndexHits<Node> entHit;
		
		entHit = entities.get("name, type", entity.getName() + ", " + entity.getType());
		temp = entHit.getSingle();
		
		if(temp == null){
			rc = graphDb.createNode(entityLabel);
			rc.setProperty("name", entity.getName());
			rc.setProperty("type", entity.getType());
			rc.setProperty("counter", 1);
			rc.setProperty("idf", entity.getIdf());
		}else{
			rc = temp;
			counter = (int) rc.getProperty("counter");
			rc.removeProperty("counter");
			++counter;
			rc.setProperty("counter", counter);
		}
		
		entities.add(rc, "name, type", entity.getName() + ", " + entity.getType());
		
		tx.success();
		tx.close();
		return rc;
	}

	public void createConnection(Node city, Node entity, int count) {
		Transaction tx = graphDb.beginTx();	
		Relationship relationship;
		
		relationship = entity.createRelationshipTo(city, RelTypes.IN);
		relationship.setProperty("count", count);
		
		tx.success();
		tx.close();
	}

	public DataDump[] getDataEntity(Entity entity){
		Transaction tx = graphDb.beginTx();
		Node ent = null;
		Node city;
		double idf;
		ArrayList<DataDump> dpArr = new ArrayList<DataDump>();
		Relationship relation;
		Iterator<Relationship> relationships;
		Iterator<Node> entIterator;
		
		entIterator = graphDb.findNodesByLabelAndProperty(entityLabel, "nameType", entity.getName() + ", " + entity.getType().toUpperCase()).iterator();
		if(entIterator.hasNext()) ent = entIterator.next();
		if(ent == null) return null;
		idf = Double.parseDouble(ent.getProperty("idf").toString());
		relationships = ent.getRelationships().iterator();
		
		while (relationships.hasNext()) {
			relation = relationships.next(); 
			city = relation.getEndNode();
			dpArr.add(new DataDump(new City(city.getProperty("name").toString(), Double.parseDouble(city.getProperty("latitude").toString()), Double.parseDouble(city.getProperty("longitude").toString())), Integer.parseInt(relation.getProperty("counter").toString()), idf));
		}
		
		tx.close();
		return dpArr.toArray(new DataDump[dpArr.size()]);
	}
	
	public void shutdown() {
		graphDb.shutdown();
	}
}
