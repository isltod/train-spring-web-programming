package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.domain.Inventory;
import com.mycompany.ordersystem.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
