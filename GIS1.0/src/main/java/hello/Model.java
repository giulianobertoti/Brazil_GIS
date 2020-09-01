package hello;

import java.util.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class Model {
	
	MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/ihc"); 
    MongoClient client = new MongoClient();
	
	public void addDocuments(Document doc){
		MongoDatabase db = client.getDatabase(uri.getDatabase());
		MongoCollection<Document> researches = db.getCollection("researches");
    	researches.insertOne(doc);
	}
	
      
	public Document searchByResearchYearCity(String nome, String ano, String geocodigo) {
		MongoDatabase db = client.getDatabase(uri.getDatabase());
    	MongoCollection<Document> researches = db.getCollection("researches");
		Document doc = researches.aggregate(Arrays.asList(unwind("$municipio"),
					   match(and(eq("nome", nome),eq("ano", ano),eq("municipio.geocodigo", geocodigo))),project(fields(include("municipio")))
					   )).first();
		return doc;
	}
   
}