package com.j0k3r_dev.cic_eva_peron.users.titular;

import com.j0k3r_dev.cic_eva_peron.http.request.report.TitularRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.TitularResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TitularService {

    @Autowired
    private TitularRepository titularRepository;

    public TitularResponse registryTitular(TitularRequest titularRequest){
        Titular titular = TitularMapper.INSTANCE.toTitular(titularRequest);
        titular.setBirthDate(LocalDate.parse(titularRequest.getBirthDateString()));
        titularRepository.save(titular);
        return TitularMapper.INSTANCE.toTitularResponse(titular);
    }

}
