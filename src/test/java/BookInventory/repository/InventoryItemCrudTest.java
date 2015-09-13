package BookInventory.repository;

import BookInventory.App;
import BookInventory.config.factory.InventoryItemFactory;
import BookInventory.domain.Consumtion;
import BookInventory.domain.InventoryItem;
import BookInventory.domain.Return;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/05/03.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class InventoryItemCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    @Autowired
    private InventoryRepository repository;

    @Test
    public void create() throws Exception{
        List<Consumtion> consumtionList = new ArrayList<Consumtion>();
        List<Return> returnList = new ArrayList<Return>();
        Map<String,String> values = new HashMap<String, String>();

        values.put("code","00789");
        values.put("name","Book");
        values.put("description","About a group of four intelligent individuals");

        InventoryItem inventoryItem = InventoryItemFactory
                .createInventoryItem(values, consumtionList, returnList);

        repository.save(inventoryItem);
        id=inventoryItem.getId();
        Assert.assertNotNull(inventoryItem);
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception{
        InventoryItem inventoryItem = repository.findOne(id);
        Assert.assertNotNull(inventoryItem);
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception{
        List<Consumtion> consumtionList = new ArrayList<Consumtion>();
        List<Return> returnList = new ArrayList<Return>();
        Map<String,String> values = new HashMap<String, String>();

        values.put("code","00789");
        values.put("name","Book");
        values.put("description","About a group of four intelligent individuals");

        InventoryItem inventoryItem = InventoryItemFactory
                .createInventoryItem(values,consumtionList,returnList);

        InventoryItem newInventoryItem = new InventoryItem
                .Builder(inventoryItem.getCode())
                .copy(inventoryItem)
                .name("DVD")
                .build();
        InventoryItem updateInventoryItem = repository.findOne(id);
        org.testng.Assert.assertEquals(updateInventoryItem.getName(),"DVD");
    }
    @Test(dependsOnMethods = "update")
    public void delete() throws Exception{
        InventoryItem inventoryItem = repository.findOne(id);
        repository.delete(inventoryItem);
        InventoryItem newInventoryItem = repository.findOne(id);
        org.testng.Assert.assertNull(newInventoryItem);
    }

}
