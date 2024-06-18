package com.j0k3r_dev.cic_eva_peron.report.member;

import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperMember {

    public static MapperMember INSTANCE = Mappers.getMapper(MapperMember.class);

    Member memberRequestToMember(MemberRequest memberRequest);

    MemberResponse memberToMemberResponse(Member member);

}
