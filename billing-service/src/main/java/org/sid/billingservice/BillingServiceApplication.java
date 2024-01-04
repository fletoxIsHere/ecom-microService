package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.sid.billingservice.services.CustomerRestClient;
import org.sid.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
                Collection<Product> products = productRestClient.allProducts().getContent();
                Long customerId = 1L;
                Customer customer = customerRestClient.findCustmerById(customerId);
                if(customer == null) throw new RuntimeException("customer not found");
                Bill bill = new Bill();
                bill.setBillDate(new Date());
                bill.setCustomerId(customerId);
            Bill savedbill = billRepository.save(bill);
            products.forEach(product -> {
                    ProductItem productItem = new ProductItem();
                    productItem.setProductId(product.getId());
                    productItem.setBill(savedbill);
                    productItem.setQuantity(1+ new Random().nextInt(10));
                    productItem.setPrice(product.getPrice());
                    productItem.setDiscount(Math.random());
                    productItemRepository.save(productItem);
                });
        };
    }


}
