package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

    private InventoryService inventoryService;
    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(path = "/list")
    public String list(Model model) {
        List<Inventory> inventories = inventoryService.getInventories();
        model.addAttribute("inventories", inventories);
        return "inventory/list";
    }

    @GetMapping("/stock")
    public String stock(Model model, @RequestParam("id") long id) {
        Inventory inventory = inventoryService.getInventory(id);
        model.addAttribute("inventory", inventory);
        return "inventory/stock";
    }

    @PostMapping(path = "/stock")
    public String stock(Inventory inventory) {
        inventoryService.stockInventory(inventory.getId(), inventory.getStock());
        return "redirect:/";
    }
}
