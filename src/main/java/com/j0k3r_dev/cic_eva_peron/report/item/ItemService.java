package com.j0k3r_dev.cic_eva_peron.report.item;

import com.j0k3r_dev.cic_eva_peron.http.request.report.ItemRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.ItemResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public ItemResponse createAndSave(ItemRequest itemRequest){
        Item item = ItemMapper.INSTANCE.itemRequestToItem(itemRequest);
        itemRepository.save(item);
        return ItemMapper.INSTANCE.itemToItemResponse(item);
    }

    @Transactional
    public List<ItemResponse> createAndSaveAllItems(List<ItemRequest> itemRequests){
        List<Item> items = itemRequests.stream()
                .map(ItemMapper.INSTANCE::itemRequestToItem)
                .toList();
        itemRepository.saveAll(items);
        return items.stream()
                .map(ItemMapper.INSTANCE::itemToItemResponse)
                .toList();
    }

}
