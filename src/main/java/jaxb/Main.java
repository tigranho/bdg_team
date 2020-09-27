package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        marshal();
        unmarshal();
    }

    public static void marshal() {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("a");
        customer.setAge(23);

        try {
            File file = new File("src/main/resources/customer.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(customer, file);
            marshaller.marshal(customer, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void unmarshal() {

        try {
            File file = new File("src/main/resources/customer.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Customer customer1 = (Customer) unmarshaller.unmarshal(file);
            System.out.println(customer1);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}