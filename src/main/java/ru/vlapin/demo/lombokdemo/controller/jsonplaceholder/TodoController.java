package ru.vlapin.demo.lombokdemo.controller.jsonplaceholder;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.api.TodoApiClient;
import ru.vlapin.demo.lombokdemo.jsonplaceholder.client.model.Todo;

import java.util.List;
import java.util.Objects;

@ExtensionMethod({
        Objects.class,
})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/todos")
@SuppressWarnings("java:S2259")
public class TodoController {

  TodoApiClient todoService;

  @GetMapping
  public List<Todo> todos() {
    return todoService.todos(null)
            .getBody()
            .requireNonNull();
  }

  @GetMapping("{id}")
  public Todo todo(@PathVariable Integer id) {
    return todoService.pickTodo(id)
            .getBody()
            .requireNonNull();
  }
}
