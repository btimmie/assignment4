package BookInventory.api;

/**
 * Created by student on 2015/05/17.
 */

import BookInventory.Services.SupplierService;
import BookInventory.domain.Supplier;
import BookInventory.model.SupplierResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/**")
public class SupplerPage {

    @Autowired
    private SupplierService service;

    @RequestMapping(value = "/supplier",method = RequestMethod.GET)
    public Supplier getSupplier(){
        Supplier supplier = new Supplier.Builder("ABC")
                .name("Book world").address("45 Lower Main Road Observatory").build();
        return supplier;
    }

    /*@RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public List<Supplier> getSuppliers(){

        return service.getSuppliers();
    }*/

    @RequestMapping(value = "/sup", method = RequestMethod.GET)
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
                    Link("http://localhost:3306/supplers/" + res.getResid().toString())
                    .withRel("supplier");
            res.add(order);
            hateos.add(res);
        }
        return hateos;
    }
}
