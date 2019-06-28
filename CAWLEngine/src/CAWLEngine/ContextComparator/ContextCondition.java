package CAWLEngine.ContextComparator;

import org.json.simple.JSONObject;

public class ContextCondition {
	private JSONObject jsonObject;
	
	public String getContextCondition(String subjectTemp, String predicateTemp, String objectTemp) {
		String subject = subjectTemp.replaceAll("(\n|\t)", "");
		String predicate = predicateTemp.replaceAll("(\n|\t)", "");
		String object = objectTemp.replaceAll("(\n|\t)", "");
		
		jsonObject = new JSONObject();
		
		jsonObject.put("subject", subject);
		jsonObject.put("predicate", predicate);
		jsonObject.put("object", object);
		
		return jsonObject.toJSONString();
	}
}
