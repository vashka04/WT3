package com.es.core.entity.ball.stock;


import com.es.core.entity.ball.Ball;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ballId")
    private Long ballId;

    @OneToOne
    @JoinColumn(name = "ballId", referencedColumnName = "id", insertable = false, updatable = false)
    private Ball ball;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "reserved", nullable = false)
    private Integer reserved;
}
