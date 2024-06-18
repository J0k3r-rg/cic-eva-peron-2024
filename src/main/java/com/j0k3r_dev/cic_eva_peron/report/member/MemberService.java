package com.j0k3r_dev.cic_eva_peron.report.member;

import com.j0k3r_dev.cic_eva_peron.http.request.report.MemberRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.MemberResponse;
import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentification;
import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TypeOfIdentificationRepository typeOfIdentificationRepository;

    public MemberResponse createAndSaveMember(MemberRequest memberRequest){
        Member member = MapperMember.INSTANCE.memberRequestToMember(memberRequest);
        member.setCreatedAt(LocalDate.now());
        String birhdateString = memberRequest.getBirthdateString();
        member.setBirthdate(LocalDate.parse(birhdateString));
        member.setTypeOfIdentification(
                typeOfIdentificationRepository
                        .findByName(memberRequest.getTypeOfIdentificationString())
                        .orElse(null)
        );
        memberRepository.save(member);
        MemberResponse response = MapperMember.INSTANCE.memberToMemberResponse(member);
        response.setAge(String.valueOf(LocalDate.now().getYear() - member.getBirthdate().getYear()));
        response.setTypeOfIdentificationString(member.getTypeOfIdentification().getName());
        return response;
    }

}
