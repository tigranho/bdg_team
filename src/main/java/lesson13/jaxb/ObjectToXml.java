package lesson13.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ObjectToXml {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        JAXBContext contextObj = JAXBContext.newInstance(Employee.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Employee emp = new Employee(1, "Adam", 50000);
        marshallerObj.marshal(emp, new FileOutputStream("emp.xml"));
    }
}
