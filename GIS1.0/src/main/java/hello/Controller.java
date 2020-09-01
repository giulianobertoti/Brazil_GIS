package hello;

import static spark.Spark.get;

import org.bson.Document;

public class Controller {
	
	private Model model;
	
	public Controller(Model model){
		this.model = model;
	}
	
	public void searchCity(){
		get("/:research/:year/:city",(req,res) -> {
			Document doc = model.searchByResearchYearCity(req.params(":research"), req.params(":year"), req.params(":city"));
			if(doc != null){
				return doc.toJson();
			} else {
				return "null";
			}
			
		});
	}

}
