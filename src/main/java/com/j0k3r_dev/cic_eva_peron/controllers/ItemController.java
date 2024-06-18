package com.j0k3r_dev.cic_eva_peron.controllers;

import com.j0k3r_dev.cic_eva_peron.http.request.report.ItemRequest;
import com.j0k3r_dev.cic_eva_peron.report.item.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PreAuthorize("hasAuthority('Permission_CREATE_ITEM')")
    @PostMapping("/create")
    public ResponseEntity<?> createAndSaveItem(@RequestBody @Valid ItemRequest itemRequest){
        return  ResponseEntity.ok(itemService.createAndSave(itemRequest));
    }


}
