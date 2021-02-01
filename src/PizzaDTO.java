import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PizzaDTO {
    private int no;
    private String name;
    private double price;
    private LocalDateTime time;
    private List<String> ingredients;

    public PizzaDTO(int no, String name, double price, String... ingredients) {
        this.no = no;
        this.name = name;
        this.price = price;
        this.ingredients = new ArrayList<>();
        for(String ingredient : ingredients){
            this.ingredients.add(ingredient);
        }
    }
    public int getNo() { return no; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public List<String> getIngredients() { return ingredients; }

    @Override
    public String toString() {
        //String output = String.format("%02d %-15s kr. %5.2f", no, name, price);
        String output = String.format("%d;%s;%5.2f;", no, name, price);
        if(ingredients.isEmpty()) return output;
        output += ingredients.get(0);
        for (int i = 1; i < ingredients.size(); i++) {
            output += ", "+ingredients.get(i);
        }
        return output;
    }
}

