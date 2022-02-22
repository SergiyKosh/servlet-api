package ua.api.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private Integer salary;
    private Long departmentId;
    private Long chiefId;

    public Employee(String name, int salary, Long departmentId, Long chiefId) {
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
        this.chiefId = chiefId;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %d\nname: %s\nsalary: %d\ndepartment_id: %d\nchief_id: %d\n",
                id,
                name,
                salary,
                departmentId,
                chiefId
        );
    }
}
