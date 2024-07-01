package com.j0k3r_dev.cic_eva_peron;

import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentification;
import com.j0k3r_dev.cic_eva_peron.normalization.TypeOfIdentificationRepository;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionEntity;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionRepository;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleEntity;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleRepository;
import com.j0k3r_dev.cic_eva_peron.users.Employee;
import com.j0k3r_dev.cic_eva_peron.users.EmployeeRepository;
import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class CicEvaPeronApplication {

	public static void main(String[] args) {
		SpringApplication.run(CicEvaPeronApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TypeOfIdentificationRepository typeOfIdentificationRepository;

//	@Bean
//	CommandLineRunner init(){
//		return args -> {
//			List<PermissionEntity> permissionUser = Stream.of(
//					"PROFILE_USER","UPDATE_PROFILE").map(
//					authority ->
//							permissionRepository.save(
//									PermissionEntity.builder()
//											.name(authority)
//											.build()
//							)
//			).toList();
//
//			Stream.of(
//					"DNI", "PASAPORTE", "CARNET DE EXTRANJERIA"
//			).forEach(
//					type ->
//							typeOfIdentificationRepository.save(
//									TypeOfIdentification.builder()
//											.name(type)
//											.build()
//							)
//			);
//
//			roleRepository.save(
//					RoleEntity.builder()
//							.name("USER")
//							.permissions(permissionUser)
//							.build()
//			);
//
//			roleRepository.save(
//					RoleEntity.builder()
//							.name("TITULAR")
//							.build()
//			);
//
//			Stream.of(
//					"GET_ROLE","CREATE_ROLE",
//					"CREATE_PERMISSION","GET_PERMISSION",
//					"CREATE_ITEM","CREATE_MEMBER","CREATE_REPORT",
//					"CREATE_TITULAR","UPDATE_TITULAR"
//			).forEach(
//					authority ->
//							permissionRepository.save(
//									PermissionEntity.builder()
//											.name(authority)
//											.build()
//							)
//			);
//
//			RoleEntity role = roleRepository.save(
//					RoleEntity.builder()
//							.name("ROOT")
//							.permissions(
//									permissionRepository.findAll()
//							)
//							.build()
//			);
//
//			employeeRepository.save(
//					Employee.builder()
//							.username("j0k3r")
//							.password(passwordEncoder.encode("123456"))
//							.role(role)
//							.enable(true)
//							.build()
//			);
//		};
//
//	}

}
