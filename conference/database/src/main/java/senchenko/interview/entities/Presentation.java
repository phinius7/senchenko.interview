package senchenko.interview.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import senchenko.interview.entities.views.CommonView;
import senchenko.interview.entities.views.ScheduleView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "presentations")
@Data
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @JsonView(ScheduleView.ScheduleInfo.class)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "presentations_authors",
            joinColumns = @JoinColumn(name = "presentation_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<User> authors;

}
