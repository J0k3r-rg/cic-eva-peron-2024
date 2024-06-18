package com.j0k3r_dev.cic_eva_peron.report.item;

import com.j0k3r_dev.cic_eva_peron.http.request.report.ItemRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    public static ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemResponse itemToItemResponse(Item item);

    Item itemRequestToItem(ItemRequest itemRequest);

}
