import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@XmlRootElement(name = "book")
@XmlType(propOrder = {"id","name","date"})
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;
    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }
    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }
    // constructor, getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book() {

    }

    public Book(Long id, String name, String author, Date date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
    }

    public static void main(String[] args) throws Exception {
        Book b = new Book();
        b.marshal();
    }

    public void marshal() throws JAXBException, IOException {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(new Date());

        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(book, new File("./book.xml"));
    }
}