package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.model.task.Task;
import com.mycompany.meowcrm.model.task.TaskState;
import com.mycompany.meowcrm.model.task.TaskType;
import com.mycompany.meowcrm.service.ITaskService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public long addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public long updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delTask(@PathVariable long id) {
        taskService.delTask(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable long id) {
        return taskService.getTask(id);
    }

    @ResponseBody
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public Map filterClients(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int items,
            @RequestParam(required = false) Integer[] type,
            @RequestParam(required = false) Integer[] state,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Long[] managers,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date to) {
        return taskService.filterClients(page, items, type, state, filter, managers, from, to);
    }

    //task types
    @ResponseBody
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public long addTaskType(@RequestBody TaskType taskType) {
        return taskService.addTaskType(taskType);
    }

    @ResponseBody
    @RequestMapping(value = "/type/{id}", method = RequestMethod.POST)
    public long updateTaskType(@RequestBody TaskType taskType) {
        return taskService.updateTaskType(taskType);
    }

    @ResponseBody
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public void delTaskType(@PathVariable int id) {
        taskService.delTaskType(id);
    }

    @ResponseBody
    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public List getTypes() {
        return taskService.getTypes();
    }

    //task states
    @ResponseBody
    @RequestMapping(value = "/state", method = RequestMethod.PUT)
    public long addTaskState(@RequestBody TaskState taskState) {
        return taskService.addTaskState(taskState);
    }

    @ResponseBody
    @RequestMapping(value = "/state/{id}", method = RequestMethod.POST)
    public long updateTaskState(@RequestBody TaskState taskState) {
        return taskService.updateTaskState(taskState);
    }

    @ResponseBody
    @RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE)
    public void delTaskState(@PathVariable int id) {
        taskService.delTaskState(id);
    }

    @ResponseBody
    @RequestMapping(value = "/state/list", method = RequestMethod.GET)
    public List getStates() {
        return taskService.getStates();
    }
}
