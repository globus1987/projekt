package pl.edu.atena.utilities;

import java.io.File;
import java.io.IOException;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.rest.StartBean;

@Stateless
public class ObjectConverter {

	public String convertToJSON(TestSuite testsuite) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testsuite);
		return jsonInString;
	}
	public static String convertRequestToJSON(StartBean startBean) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(startBean);
		return jsonInString;
	}
	

	public void saveToJSON(TestSuite testsuite) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String filename = testsuite.getName().toString();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("D:\\" + filename + ".json"), testsuite);
	}

	public void readJSONFromFile(TestSuite testsuite) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String filename = testsuite.getName().toString();
		mapper.writeValue(new File("D:\\" + filename + ".json"), testsuite);
	}

	public TestSuite convertFromJSON(String json) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		// JSON from String to Object
		TestSuite testsuite = mapper.readValue(json, TestSuite.class);
		return testsuite;
	}

	public void convertToXML(TestSuite testsuite) throws JAXBException {
		String filename = testsuite.getName().toString();
		File file = new File(("D:\\" + filename + ".xml"));
		JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(testsuite, file);

		jaxbMarshaller.marshal(testsuite, System.out);

	}
	
	public void convertFromXML() throws JAXBException {
		File file = new File("D:\\testowy suite.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		TestSuite testsuite = (TestSuite) jaxbUnmarshaller.unmarshal(file);
        System.out.println("to odczytalem z xml: " + testsuite);

	}

}
