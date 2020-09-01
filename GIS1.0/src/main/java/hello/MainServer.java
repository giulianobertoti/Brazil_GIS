package hello;

import static spark.Spark.*;

import org.bson.Document;



public class MainServer {
	
	final static Model model = new Model();
	
    public static void main(String[] args) {

		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 8000;
        }
        port(port);

		//Server static files
		staticFileLocation("/static");

		initializeDataset();

		Controller controller = new Controller(model);
		
		controller.searchCity();
		
    }
    
    
    public static void initializeDataset(){

		    	
    	
    	
    	
    	Document document = Document.parse(biomas);
    	
    	Document document2 = Document.parse(ana);
    	
    	Document document3 = Document.parse(queimadas);

    	model.addDocuments(document);
    	
    	model.addDocuments(document2);
    	
    	model.addDocuments(document3);
    }
}