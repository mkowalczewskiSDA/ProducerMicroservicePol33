package com.example.ProducerMicroservice

import com.example.ProducerMicroservice.model.dto.UserDTO
import com.example.ProducerMicroservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TaskControllerSpec extends Specification {

    @Autowired
    UserService userService;

    def setup() {
        userService.create(new UserDTO("Stefan", "Nowak", "sn@wp.pl"))
        userService.create(new UserDTO("Jan", "Kowalski", "jk@wp.pl"))
    }

}
