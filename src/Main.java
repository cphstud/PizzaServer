import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    Random rd = new Random();
    //Pizza[] pizzas;
    //Pizza[] pizzas;
    ArrayBlockingQueue<Pizza> pizzas;
    public final List<PizzaDTO> menu;

    public Main() {

        pizzas = new ArrayBlockingQueue<>(256);
        //pizzas = new Pizza[256];
        //pizzas = new ArrayList<>();
        this.menu = Util.retMenu();
    }

    public static void main(String[] args) {
        // write your code here
        Main main = new Main();
        main.runProgram();
    }


    public void runProgram() {
        Thread tb = new Thread(new PizzaBaker(pizzas,menu));
        Thread tc = new Thread(new PizzaCustomer(pizzas,menu));
        Thread tc2 = new Thread(new PizzaCustomer(pizzas,menu));
        Thread tc3 = new Thread(new PizzaCustomer(pizzas,menu));
        Thread tc4 = new Thread(new PizzaCustomer(pizzas,menu));
        tb.start();
        try {
            tb.join(4000);
        } catch (InterruptedException ie) {
            ie.getStackTrace();
        }
        tc.setName("Lone");
        tc2.setName("Viggo");
        tc3.setName("Arne");
        tc4.setName("Ib");
        tc.start();
        tc2.start();
        tc3.start();
        tc4.start();
    }

}

class PizzaBaker implements Runnable{
    //List<Pizza> pizzas;
    ArrayBlockingQueue<Pizza> pizzas;
    List<PizzaDTO> menu;

    PizzaBaker(ArrayBlockingQueue<Pizza> pizzas, List<PizzaDTO> menu) {
        this.pizzas = pizzas;
        this.menu = menu;
    }
    @Override
    public void run() {
        int[] choices = {0,1,2,3,4};
        int counter = 0;
        int pos=0;
        PizzaDTO p = null;
        Pizza pz = null;
        while(true) {
            counter++;
            p = menu.get(choices[counter%4]);
            pz = new Pizza(p);
            //pos = vaccantPos(pizzas);
            pizzas.add(pz);

            //pizzas[pos]=pz;
            System.out.println(Util.ANSI_PURPLE+"BAKER SLEEPS after adding " + pz.getName() + ", no " + pz.getNo() );
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Util.ANSI_PURPLE+"BAKER INTERRUPTED");
            }
        }
    }
    public int vaccantPos(Pizza[] p) {
        int retVal = 0;
        while(p[retVal]!=null) {
            retVal++;
        }
        return retVal;
    }
}

class PizzaCustomer implements Runnable {
    //List<Pizza> pizzas;
    ArrayBlockingQueue<Pizza> pizzas;
    List<PizzaDTO> menu;

    PizzaCustomer(ArrayBlockingQueue<Pizza> pizzas, List<PizzaDTO> menu) {
        this.pizzas = pizzas;
        this.menu = menu;
    }
    @Override
    public void run() {
        int[] choices = {1,2,3,4,5};
        int counter = 0;
        int pos = 0;
        PizzaDTO p = null;
        Pizza pz = null;
        String col = "";
        switch (Thread.currentThread().getName()) {
            case "Lone":col=Util.ANSI_BLUE;break;
            case "Viggo":col=Util.ANSI_CYAN;break;
            case "Arne":col=Util.ANSI_BLACK;break;
            case "Ib":col=Util.ANSI_RED;break;
            default:col=Util.ANSI_GREEN;
        }
        while(true) {
            p = menu.get(choices[counter%((choices.length)-1)]);
            //kpos=vaccantPos(pizzas);
            for(Pizza pizza:  pizzas) {
                System.out.println(col+"Customer " + Thread.currentThread().getName() + " will look at .."+pizza.getName()+", "+pizza.getNo() + " look for " +p.getNo());
                if(pizza.getNo() == p.getNo() ) {
                    System.out.println(col+"Customer " + Thread.currentThread().getName() + " will eat .."+p.getName()+", "+p.getNo());
                    pizzas.remove(pizza);
                }
            }
            System.out.println(col+"CUSTOMER SLEEPS");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("BAKER INTERRUPTED");
            }
            counter++;
        }
    }
    public int vaccantPos(Pizza[] p) {
        int retVal = 0;
        while(p[retVal]!=null) {
            retVal++;
        }
        return retVal;
    }
}
