package sk.umb.example.library.category.controller;

public class CategoryRequestDataTransferObject {

    private String name;
    private String[] categoryIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }
}
