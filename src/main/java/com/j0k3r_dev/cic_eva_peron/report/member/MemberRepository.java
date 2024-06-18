package com.j0k3r_dev.cic_eva_peron.report.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByIdentificationNumber(String identificationNumber);

}
