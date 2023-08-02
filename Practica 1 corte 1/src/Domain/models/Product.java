package Domain.models;

public class Product {
    private Long id;
    private String name;
    private String Category;
    private Double price;
    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        Category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return Category;
    }

    public Double getPrice() {
        return price;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
