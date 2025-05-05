package edu.hitwh.oremanagementserver.controller;

import edu.hitwh.oremanagementserver.domain.Market;
import edu.hitwh.oremanagementserver.domain.MarketItem;
import edu.hitwh.oremanagementserver.domain.User;
import edu.hitwh.oremanagementserver.dto.MarketItemList;
import edu.hitwh.oremanagementserver.dto.MarketList;
import edu.hitwh.oremanagementserver.service.MarketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MarketController {

    @Autowired
    MarketService marketService;

    @GetMapping("/current")
    public Result getUsersMarkets(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        return marketService.getUsersMarkets(user.getId());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Market market, HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user");
        market.setUserId(user.getId());
        return marketService.add(market);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody MarketList marketList, HttpServletRequest request, HttpServletResponse response){
        return marketService.delete(marketList);
    }

    @PostMapping("/items/add")
    public Result addItem(@RequestBody MarketItemList marketItemList, HttpServletRequest request, HttpServletResponse response){
        return marketService.addItems(marketItemList);
    }

    @GetMapping("/items/current")
    public Result getUsersMarketsItems(@RequestParam Long id,HttpServletRequest request, HttpServletResponse response){
        return marketService.getMarketsItems(id);
    }

}
