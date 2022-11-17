package com.example.trelloapp.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

@Getter
@Setter
@NoArgsConstructor
@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer position;
    private String name;
    private String description;
    private LocalDateTime localDateTime;
    @Cascade(value = CascadeType.ALL)
    @OneToMany(mappedBy = "table")
    @OrderBy(clause = "position ASC")
    private List<Task> tasks;
    @ManyToOne
    private User user;
}
