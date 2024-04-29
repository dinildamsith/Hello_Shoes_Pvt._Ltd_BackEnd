package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
    void returnOrder(ReturnDto returnDto);
}
