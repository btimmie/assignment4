package BookInventory.repository;

import BookInventory.App;
import BookInventory.config.factory.ManagerFactory;
import BookInventory.domain.Manager;
import org.testng.Assert;
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
public class ManagerCrudTest extends AbstractTestNGSpringContextTests{
    private Long id;

    @Autowired
    private ManagerRepository repository;

    @Test
    public void create() throws Exception{
        Map<String,String> values = new HashMap<String,String>();

        values.put("code","D15C");
        values.put("fistName","Felicity");
        values.put("lastName","Smoak");

        Manager manager = ManagerFactory
                .createManager(values);

        repository.save(manager);
        id=manager.getId();
        Assert.assertNotNull(manager);
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        Manager manager = repository.findOne(id);
        Assert.assertNotNull(manager);
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception{
        Manager manager = repository.findOne(id);

        Manager newManager = new Manager
                .Builder(manager.getCode())
                .firstName("Felicity")
                .lastName("Queen")
                .build();

        Manager updateManager = repository.findOne(id);
        org.testng.Assert.assertEquals(updateManager.getLastName(),"Queen");
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception{
            Manager manager = repository.findOne(id);
            repository.delete(manager);
            Manager deletedManager = repository.findOne(id);
            Assert.assertNull(deletedManager);
    }

}
