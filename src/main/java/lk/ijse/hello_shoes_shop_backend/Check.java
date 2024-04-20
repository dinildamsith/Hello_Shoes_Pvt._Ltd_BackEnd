package lk.ijse.hello_shoes_shop_backend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class Check {

    @PostMapping
    void check(){
        System.out.println("Working !!");
    }
}
