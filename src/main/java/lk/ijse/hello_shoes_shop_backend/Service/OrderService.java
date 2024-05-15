package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
    void returnOrder(ReturnDto returnDto);

    String getLastOrderId();

    List<OrderDto> getAllOrders();


}
