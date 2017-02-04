package com.mycompany.meowcrm.model.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.meowcrm.model.BasicMarker;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "tasks"})
public class TaskType extends BasicMarker implements Serializable {

    private Set<Task> tasks = new HashSet<>(0);

    public TaskType() {
    }

    public TaskType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TaskType(int id, String name, String icon, String description, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "type")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
