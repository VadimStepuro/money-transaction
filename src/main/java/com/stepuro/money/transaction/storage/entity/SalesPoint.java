package com.stepuro.money.transaction.storage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "sales_point", schema = "money_transaction")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SalesPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String address;
    private double longitude;
    private double latitude;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}
