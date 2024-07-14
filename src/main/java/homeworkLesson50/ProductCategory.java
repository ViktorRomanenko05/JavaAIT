package homeworkLesson50;

public enum ProductCategory {
    ERSONAL_ELECTRONICS ("Персональная электроника"),
    ACCESSORIES ("Аксессуары"),
    HOME_APPLIANCES ("Бытовая техника"),
    COMPUTERS ("Компьютеры и комплектующие"),
    MULTIMEDIA("Мультимедиа устройства");

    private final String description;

    ProductCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
