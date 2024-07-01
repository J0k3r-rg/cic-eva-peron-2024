package com.j0k3r_dev.cic_eva_peron.users.titular;

import com.j0k3r_dev.cic_eva_peron.http.request.report.TitularRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.report.TitularResponse;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleEntity;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TitularService {

    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public TitularResponse registryTitular(TitularRequest titularRequest){
        Titular titular = TitularMapper.INSTANCE.toTitular(titularRequest);
        titular.setBirthDate(LocalDate.parse(titularRequest.getBirthDateString()));
        titular.setUsername(titularRequest.getIdentificationNumber());
        titular.setPassword(passwordEncoder.encode(titularRequest.getIdentificationNumber()));
        RoleEntity role = roleRepository.findByName("TITULAR").orElse(null);
        titular.setRole(role);
        titularRepository.save(titular);
        return TitularMapper.INSTANCE.toTitularResponse(titular);
    }

    @Transactional
    public TitularResponse updateTitular(TitularRequest titularRequest) throws TitularException {
        Optional<Titular> titularOptional = titularRepository.findById(titularRequest.getId());
        if ( titularOptional.isEmpty())
            throw new TitularException("No se encontro el titular", 404);
        Titular titular = titularOptional.get();
        titular.setNames(titularRequest.getNames());
        titular.setLastNames(titularRequest.getLastNames());
        titular.setTypeOfIdentification(titularRequest.getTypeOfIdentification());
        titular.setIdentificationNumber(titularRequest.getIdentificationNumber());
        titular.setBirthDate(LocalDate.parse(titularRequest.getBirthDateString()));
        titular.setBirthplace(titularRequest.getBirthplace());
        titular.setNationality(titularRequest.getNationality());
        titular.setPhone(titularRequest.getPhone());
        titular.setAddress(titularRequest.getAddress());
        titular.setNeighborhood(titularRequest.getNeighborhood());
        titular.setCity(titularRequest.getCity());
        titular.setPostalCode(titularRequest.getPostalCode());
        return TitularMapper.INSTANCE.toTitularResponse(titular);
    }

}
