package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size")
public class StockEntity implements SuperEntity{

    @Id
    private String stockId;
    private String itemSize;
    private String qty;
    //
    @ManyToOne
    @JoinColumn(name = "itemId")
    private ItemEntity itemEntititys;

//    @OneToMany(mappedBy = "size")
//    private List<StockEntity> stock;
}
