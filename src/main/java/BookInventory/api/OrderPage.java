package BookInventory.api;

import BookInventory.Services.SupplierOrderService;
import BookInventory.Services.SupplierService;
import BookInventory.domain.Order;
import BookInventory.domain.Supplier;
import BookInventory.model.SupplierResource;
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
@RequestMapping(value="/order/**")
public class OrderPage {
    @Autowired
    private SupplierOrderService service1;
    @Autowired
    private SupplierService service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Order> getOrders(@PathVariable Long id){
        return service1.getOrders(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public List<SupplierResource> getSuppliers() {
        List<SupplierResource> hateos = new ArrayList<>();
        List<Supplier> suppliers = service.getSuppliers();
        for (Supplier supplier : suppliers) {
            SupplierResource res = new SupplierResource
                    .Builder(supplier.getName())
                    .name(supplier.getName())
                    .address(supplier.getAddress())
                    .orderlist(supplier.getOrderList())
                    .id(supplier.getId())
                    .build();
            Link order = new
                    Link("http://localhost:3306/orders/" + res.getResid().toString())
                    .withRel("orders");
            res.add(order);
            hateos.add(res);
        }
        return hateos;
    }
}