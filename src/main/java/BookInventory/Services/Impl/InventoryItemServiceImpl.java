package BookInventory.Services.Impl;

import BookInventory.Services.InventoryItemService;
import BookInventory.domain.Consumtion;
import BookInventory.domain.InventoryItem;
import BookInventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
public class InventoryItemServiceImpl implements InventoryItemService {

    @Autowired
    private InventoryRepository repository;


    @Override
    public List<InventoryItem> getReturns() {
        List<InventoryItem> items = new ArrayList<>();
        Iterable<InventoryItem> values = repository.findAll();
        for(InventoryItem value : values) {
            items.add(value);
        }
        return items;
    }

    @Override
    public List<Consumtion> getAllConsumtions(Long id) {
        return repository.findOne(id).getConsumtionList();
    }
}
