package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class StockEntity {
    @Id
    private String stockId;

    @ManyToOne
    @JoinColumn(name = "size")
    private SizeEntity size;
    @ManyToOne
    @JoinColumn(name = "item")
    private ItemEntity item;
}
