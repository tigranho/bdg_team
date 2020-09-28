package tasks.xmlAndJaxb;

import lombok.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "book")
@XmlType(propOrder = {"id", "name", "author"})
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;

    @XmlElement(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlTransient
    public void setDate(Date date) {
        this.date = date;
    }

    public static void marshal(Book book) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(book, new File("src\\main\\java\\tasks\\xmlAndJaxb\\book.xml"));
    }

    public static Book unmarshal() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        return (Book) context.createUnmarshaller()
                .unmarshal(new FileReader("src\\main\\java\\tasks\\xmlAndJaxb\\book.xml"));
    }

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(new Date());

        marshal(book);

        book = unmarshal();
        System.out.println(book);
    }
}