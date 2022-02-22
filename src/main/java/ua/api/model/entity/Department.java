package ua.api.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id: %d\nname: %s\n", id, name);
    }
}