public class Clothing implements Comparable<Clothing> {
    private final String brand;
    private final String type;
    private final String size;
    private final double price;
    private final String color;
    private final boolean isUnisex;

    /**
     * Конструктор.
     */
    public Clothing(String brand, String type, String size, double price, String color, boolean isUnisex) {
        this.brand = brand;
        this.type = type;
        this.size = size;
        this.price = price;
        this.color = color;
        this.isUnisex = isUnisex;
    }

    public String getBrand() { return brand; }
    public String getType() { return type; }
    public String getSize() { return size; }
    public double getPrice() { return price; }
    public String getColor() { return color; }
    public boolean isUnisex() { return isUnisex; }

    /**
     * Порівняння за ціною (за зростанням) — для Comparable.
     */
    @Override
    public int compareTo(Clothing other) {
        return Double.compare(this.price, other.price);
    }

    /**
     * Перевірка ідентичності об’єктів (всі поля).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clothing)) return false;
        Clothing that = (Clothing) o;
        return Double.compare(that.price, price) == 0 &&
                isUnisex == that.isUnisex &&
                brand.equals(that.brand) &&
                type.equals(that.type) &&
                size.equals(that.size) &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + color.hashCode();
        result = 31 * result + (isUnisex ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s, %.2f грн, %s, %s)",
                brand, type, size, price, color, isUnisex ? "унісекс" : "не унісекс");
    }
}