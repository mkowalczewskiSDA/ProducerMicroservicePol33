package com.example.ProducerMicroservice

import com.example.ProducerMicroservice.model.dto.TaskDTO
import com.example.ProducerMicroservice.model.dto.UserDTO
import com.example.ProducerMicroservice.service.TaskService
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
    UserService userService
    @Autowired
    TaskService taskService

    def setup() {
        userService.create(new UserDTO("Stefan", "Nowak", "sn@wp.pl"))
        userService.create(new UserDTO("Jan", "Kowalski", "jk@wp.pl"))
        taskService.create(new TaskDTO("test1", true))
        taskService.create(new TaskDTO("test2", false, 20, new UserDTO(2, "Jan", "Kowalski", "jk@wp.pl")))
        taskService.create(new TaskDTO("test3", false, 50, new UserDTO(1, "Stefan","Nowak", "sn@wp.pl")))
    }

}
