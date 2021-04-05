import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurants.stream().filter(res -> res.getName().equals(restaurantName)).findAny();
        Restaurant restaurant = null;
        if(restaurantOptional.isPresent()) {
            restaurant = restaurantOptional.get();
        }
        else {
            throw new RestaurantNotFoundException("The restaurant named: " + restaurantName + "is not found");
        }
        return restaurant;
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws RestaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if(Objects.nonNull(restaurantToBeRemoved)) {
            restaurants.remove(restaurantToBeRemoved);
            return restaurantToBeRemoved;
        }
        else{
            throw new RestaurantNotFoundException("The restaurant named: " + restaurantName + "is not found");
        }
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}
