package BookInventory.api;

import BookInventory.Services.InventoryItemService;
import BookInventory.domain.Consumtion;
import BookInventory.domain.InventoryItem;
import BookInventory.model.InventoryItemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
@RestController
@RequestMapping(value="/inventory/**")
public class InventoryPage {

    @Autowired
    private InventoryItemService service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Consumtion> getConsumtions(@PathVariable Long id){
        return service.getAllConsumtions(id);
    }

    @RequestMapping(value = "/inventories", method = RequestMethod.GET)
    public List<InventoryItemResource> getInventoryItems(){
        List<InventoryItemResource> hateos = new ArrayList<>();
        List<InventoryItem> inventoryItems = service.getReturns();
        for (InventoryItem inventoryItem : inventoryItems) {
            InventoryItemResource res = new InventoryItemResource
                    .Builder(inventoryItem.getCode())

                    .name(inventoryItem.getName())
                    .description(inventoryItem.getDescription())
                    .consumtionList(inventoryItem.getConsumtionList())
                    .build();
            Link consumtions = new
                    Link("http://localhost:3306/inventories/"+res.getResid().toString())
                    .withRel("cons");
            res.add(consumtions);
            hateos.add(res);
        }
        return hateos;
    }

}
