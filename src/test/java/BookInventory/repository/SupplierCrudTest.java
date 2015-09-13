package BookInventory.repository;

import BookInventory.App;
import BookInventory.domain.InventoryItem;
import BookInventory.domain.Supplier;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/05/03.
 */


@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class SupplierCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    private SupplierRepository repository;
    @Test
    public void aCreate() throws Exception{
        /*List<InventoryItem> inventoryItemList = new ArrayList<InventoryItem>();
        Map<String,String>values = new HashMap<String, String>();
        values.put("code", "CNA007");
        values.put("name","CNA");
        Supplier supplier = SupplierFactory
                .createSuplier(values,inventoryItemList);
        Assert.assertNotNull(supplier);*/
        List<InventoryItem> inventoryItemList = new ArrayList<InventoryItem>();
        Supplier supplier = new Supplier.Builder("ABC")
        .name("Book world").address("45 Lower Main Road Observatory").build();

        repository.save(supplier);
        id=supplier.getId();
        Assert.assertNotNull(supplier.getId());
    }

    @Test
    public void bReadTest() throws Exception{
        Supplier supplier =  repository.findOne(id);
        Assert.assertEquals("Book world",supplier.getName());
    }
    @Test
    public void cTestUpdate() throws Exception{
        Supplier supplier = repository.findOne(id);
        Supplier newSupplier = new Supplier.Builder("BBC").id(supplier.getId())
                .name("Book worldz").address("49 Loop Street Cape Town").build();
        repository.save(supplier);
        Assert.assertEquals("Book worldz",supplier.getName());
        Assert.assertEquals("49 Loop Street Cape Town",supplier.getAddress());
    }
    @Test
    public void dTestDelete() throws Exception{
        Supplier supplier = repository.findOne(id);
        repository.delete(supplier);
        Supplier newSupplier = repository.findOne(id);
        Assert.assertNull(newSupplier);
    }
}