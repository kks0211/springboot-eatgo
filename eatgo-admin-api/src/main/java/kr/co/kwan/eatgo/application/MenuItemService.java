package kr.co.kwan.eatgo.application;

import kr.co.kwan.eatgo.domain.MenuItem;
import kr.co.kwan.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemsRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemsRepository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            update(restaurantId, menuItem);
        }
        //MenuItem menuItem = MenuItem.builder().name("Kimchi").build();
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestory()){
            menuItemsRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemsRepository.save(menuItem);
    }

}
