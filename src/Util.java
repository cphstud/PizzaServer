import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    // cols
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static List<PizzaDTO> retMenu() {
        List<PizzaDTO> menu = new ArrayList<>();



        File file = new File("resources/menu");
        //11,Hawai,Tomatsauce|ost|skinke|ananas|oregano,61
        String line = "";
        PizzaDTO tmpPizza = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((line=br.readLine())!=null) {
                String[] lineArr = line.split(",");
                String[] ingredients = lineArr[2].split("|");
                String ingr = String.join("",ingredients);
                tmpPizza = new PizzaDTO(Integer.valueOf(lineArr[0]),lineArr[1],Double.valueOf(lineArr[3]),ingr);
                menu.add(tmpPizza);
            }
        } catch (
                IOException e ) {
            e.printStackTrace();
        }
        return menu;
    }
}
