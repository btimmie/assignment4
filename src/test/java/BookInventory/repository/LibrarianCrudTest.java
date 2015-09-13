package BookInventory.repository;

import BookInventory.App;
import BookInventory.config.factory.LibrarianFactory;
import BookInventory.domain.Librarian;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 2015/05/03.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class LibrarianCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    private LibrarianRepository repository;

    @Test
    public void create() throws Exception{
        Map<String,String> values = new HashMap<String,String>();

        values.put("code","D15C");
        values.put("fistName","Felicity");
        values.put("lastName","Smoak");

        Librarian librarian = LibrarianFactory
                .createLibrarian(values);

        repository.save(librarian);
        id=librarian.getId();

        org.testng.Assert.assertNotNull(librarian);
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        Librarian librarian = repository.findOne(id);
        Assert.assertNotNull(librarian);
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception{
        Librarian librarian = repository.findOne(id);

        Librarian newLibrarian = new Librarian
                .Builder(librarian.getCode())
                .firstName("Felicity")
                .lastName("Queen")
                .build();

        Librarian updateLibrarian = repository.findOne(id);
        org.testng.Assert.assertEquals(updateLibrarian.getLastName(),"Queen");
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception{
        Librarian librarian = repository.findOne(id);
        repository.delete(librarian);
        Librarian deletedLibrarian = repository.findOne(id);
        org.testng.Assert.assertNull(deletedLibrarian);
    }

}
