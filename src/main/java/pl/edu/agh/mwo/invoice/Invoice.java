package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public Invoice() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : products) {
            subtotal = subtotal.add(product.getPrice());
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal taxAmount = BigDecimal.ZERO;
        for (Product product : products) {
            BigDecimal taxForProduct = product.getPrice().multiply(product.getTaxPercent());
            taxAmount = taxAmount.add(taxForProduct);
        }
        return taxAmount;
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }
}
