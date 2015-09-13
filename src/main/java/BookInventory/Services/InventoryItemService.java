package BookInventory.Services;

import BookInventory.domain.Consumtion;
import BookInventory.domain.InventoryItem;

import java.util.List;

/**
 * Created by student on 2015/05/17.
 */
public interface InventoryItemService {
    List<InventoryItem> getReturns();
    List<Consumtion> getAllConsumtions(Long id);

}
