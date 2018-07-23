package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/add")
    public String add(@RequestParam Long cellphoneId,
                      @AuthenticationPrincipal(expression = "user.id") Long userId) {
        // 注意：SecurityConfig中配置的/uc/**需要登录保证了到达此控制器方法时，必定有用户已登录，这样才能顺利拿到userId
        shoppingCartService.addToCart(userId, cellphoneId, 1);
        return "redirect:/cellphones/" + cellphoneId;
    }
}
