package BookInventory.Services;

import BookInventory.App;
import BookInventory.config.factory.InventoryItemFactory;
import BookInventory.domain.Consumtion;
import BookInventory.domain.InventoryItem;
import BookInventory.domain.Return;
import BookInventory.repository.InventoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student on 2015/05/17.
 */
@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class InventoryServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private InventoryItemService service;
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

    @Test
    public void testConsumtion() throws Exception{
        List<Consumtion> consumtions = service.getAllConsumtions(id);
        Assert.assertTrue(consumtions.size() == 0);
    }
}
