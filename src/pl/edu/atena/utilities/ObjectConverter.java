package pl.edu.atena.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.rest.StartBean;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
@Stateless
public class ObjectConverter {
	Logger log = Logger.getLogger("log_producer");
	public String convertToJSON(TestSuite testsuite) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testsuite);
	}

	public String convertRequestToJSON(StartBean startBean) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(startBean);
	}
	

	public void saveToJSON(TestSuite testsuite) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String filename = testsuite.getName();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("D:\\" + filename + ".json"), testsuite);
	}

	public void readJSONFromFile(TestSuite testsuite) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String filename = testsuite.getName();
		mapper.writeValue(new File("D:\\" + filename + ".json"), testsuite);
	}

	public TestSuite convertFromJSON(String json) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		// JSON from String to Object

		return mapper.readValue(json, TestSuite.class);
	}

	public void convertToXML(TestSuite testsuite) throws JAXBException {
		String filename = testsuite.getName();
		File file = new File(("D:\\" + filename + ".xml"));
		JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(testsuite, file);
	}
	
	public void convertFromXML() throws JAXBException {
		String path = "D:\\testowy suite.xml";
		File file = new File(path);
		JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		TestSuite testsuite = (TestSuite) jaxbUnmarshaller.unmarshal(file);
		log.error("to odczytalem z xml: " + testsuite);

	}

}
