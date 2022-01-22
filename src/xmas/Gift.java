package xmas;

import com.google.gson.annotations.Expose;
import enums.Category;

import java.io.Serializable;

public class Gift implements Comparable<Gift>, Serializable {
    private final String productName;
    private final Double price;
    private Category category;
    @Expose(serialize = false)
    private Integer quantity;

    public Gift(final String productName,
                final Double price,
                final Category category,
                final Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public final String getProductName() {
        return productName;
    }

    public final Double getPrice() {
        return price;
    }

    public final Category getCategory() {
        return category;
    }

    public final void setCategory(Category category) {
        this.category = category;
    }

    public final Integer getQuantity() {
        return quantity;
    }

    public final void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public final String toString() {
        return "Gift{"
                + "productName='" + productName + '\''
                + ", price=" + price
                + ", category=" + category
                + ", quantity=" + quantity + '}';
    }

    @Override
    public final int compareTo(final Gift other) {
        if (this.getPrice() < other.getPrice()) {
            return -1;
        }
        return 1;
    }

    public final void decreaseQuantity() {
        --quantity;
    }
}
