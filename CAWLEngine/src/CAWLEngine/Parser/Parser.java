package CAWLEngine.Parser;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import CAWLEngine.WorkflowPool.WorkflowPool;

public class Parser {

	public void parse(File xmlFilePath, WorkflowPool workflowPool) {
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		
		File xsdFile;
		Schema schema;
		Validator validator;
		
		try {
			xsdFile = new File("C:\\Dev\\workspace-cawl_engine_ys\\CAWLEngine\\CAWL_Schema\\CAWLSchema.xsd");
			schema = factory.newSchema(xsdFile);
			
			validator = schema.newValidator();
			
			Source source = new StreamSource(xmlFilePath);
			
			validator.validate(source);
			
			ParserHandler parserHandler = new ParserHandler();
			parserHandler.parsing(xmlFilePath, workflowPool);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
