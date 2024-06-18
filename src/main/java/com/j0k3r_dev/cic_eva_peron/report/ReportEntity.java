package com.j0k3r_dev.cic_eva_peron.report;

import com.j0k3r_dev.cic_eva_peron.report.item.Item;
import com.j0k3r_dev.cic_eva_peron.report.member.Member;
import com.j0k3r_dev.cic_eva_peron.users.Employee;
import com.j0k3r_dev.cic_eva_peron.users.Titular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reports")
public class ReportEntity {

    @Id
    @UuidGenerator
    private String id;

    private Titular titular;

    @OneToOne
    private Employee employee;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Member> members;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;

}