import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private Clock clock;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime(clock).isAfter(openingTime) && getCurrentTime(clock).isBefore(closingTime) ? Boolean.TRUE : Boolean.FALSE;
    }

    public LocalTime getCurrentTime(Clock clock){ return  LocalTime.now(clock); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int calculateOrderValue(List<String> items) throws ItemNotFoundException {
        int price = 0;
        for(String item : items) {
            Item itemToBeSearched = findItemByName(item);
            if(Objects.isNull(itemToBeSearched)) {
                throw new ItemNotFoundException("The item " + item + " is not found");
            }
            price = price + itemToBeSearched.getPrice();
        }
        return price;
    }
}
