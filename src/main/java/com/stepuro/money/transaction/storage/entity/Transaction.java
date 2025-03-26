package com.stepuro.money.transaction.storage.entity;

import com.stepuro.money.transaction.web.dto.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "transaction", schema = "money_transaction")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private String cardMask;
    private LocalDateTime date;
    private String currencyCode;
    private UUID userId;
    @ManyToOne
    @JoinColumn(name = "sales_point_id", referencedColumnName = "id")
    private SalesPoint salesPoint;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}
