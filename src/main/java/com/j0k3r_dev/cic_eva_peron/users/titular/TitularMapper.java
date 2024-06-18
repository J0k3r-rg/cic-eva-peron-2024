package com.j0k3r_dev.cic_eva_peron.users.titular;

import com.j0k3r_dev.cic_eva_peron.http.request.report.TitularRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.TitularResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TitularMapper {

    public static TitularMapper INSTANCE = Mappers.getMapper(TitularMapper.class);

    Titular toTitular(TitularRequest titularRequest);

    TitularResponse toTitularResponse(Titular titular);

}
