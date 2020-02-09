package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.MenuItemService;
import kr.co.kwan.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems){
        //List<MenuItem> menuItems = new ArrayList<>();
        menuItemService.bulkUpdate(restaurantId, menuItems);
        return "";
    }

}
