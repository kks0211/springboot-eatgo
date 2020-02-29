package kr.co.kwan.eatgo.interfaces;

import kr.co.kwan.eatgo.application.MenuItemService;
import kr.co.kwan.eatgo.domain.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping("/restaurants/{restaurantId}/menuitems")
    public List<MenuItem> list(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItems(restaurantId);

        return menuItems;
    }

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems){
        //List<MenuItem> menuItems = new ArrayList<>();
        menuItemService.bulkUpdate(restaurantId, menuItems);
        return "";
    }

}
