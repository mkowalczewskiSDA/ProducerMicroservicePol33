package com.example.ProducerMicroservice

import com.example.ProducerMicroservice.controller.TaskController
import com.example.ProducerMicroservice.model.dto.TaskDTO
import com.example.ProducerMicroservice.model.dto.UserDTO
import com.example.ProducerMicroservice.repository.TaskRepository
import com.example.ProducerMicroservice.service.TaskService
import com.example.ProducerMicroservice.service.UserService
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TaskControllerSpec extends Specification {

    @Autowired
    UserService userService
    @Autowired
    TaskService taskService
    @Autowired
    TaskController taskController
    @Autowired
    MockMvc mockMvc

    static def task = new TaskDTO(1,"test1", true)
    static List<TaskDTO> taskList = [task,
                                  new TaskDTO(2,"test2", false, 20, new UserDTO(2, "Jan", "Kowalski", "jk@wp.pl")),
                                  new TaskDTO(3,"test3", false, 50, new UserDTO(1, "Stefan","Nowak", "sn@wp.pl"))
                                  ]
    ObjectMapper objectMapper = new ObjectMapper()

    def setup() {
        userService.create(new UserDTO("Stefan", "Nowak", "sn@wp.pl"))
        userService.create(new UserDTO("Jan", "Kowalski", "jk@wp.pl"))
        taskService.create(new TaskDTO("test1", true))
        taskService.create(new TaskDTO("test2", false, 20, new UserDTO(2, "Jan", "Kowalski", "jk@wp.pl")))
        taskService.create(new TaskDTO("test3", false, 50, new UserDTO(1, "Stefan","Nowak", "sn@wp.pl")))
    }

    def "should return list of all Tasks"() {
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get('/api/tasks')).andReturn()
        def values = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<TaskDTO>>() {})
        then:
        values == taskList
    }

    @Unroll
    def "should retrun Task by specific id = #id"() {
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get('/api/tasks/'+id)).andReturn()
        def values = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<TaskDTO>() {})
        then:
        values == testTask
        where:
        id   | testTask
        1    | taskList.get(0)
        2    | taskList.get(1)
        3    | taskList.get(2)
    }

    def "should add new Task to the database"() {
        given:
        def dto = new TaskDTO("spec", false)
        def request = objectMapper.writeValueAsString(dto)
        expect:
        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                            .content(request)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk())
        and:
        def task = taskService.findLast()
        task.description == dto.description
        task.completed == dto.completed
    }

    def "should update task"() {
        given:
        def dto = new TaskDTO(1, "taskChanged1", false)
        def request = objectMapper.writeValueAsString(dto)
        expect:
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/"+dto.id)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
        and:
        taskService.findById(dto.id) == dto
    }

    def "should check if object is correct"() {
        given:
        def service = Stub(TaskService.class)
        service.findAll() >> [new TaskDTO(1,"sprawdzam", true),
                                 new TaskDTO(2,"kolejny", false)]

        when:
        TaskController taskController = new TaskController(service)
        def list = taskController.findAll()
        then:
        list.get(0).description == "sprawdzam"

    }


}
