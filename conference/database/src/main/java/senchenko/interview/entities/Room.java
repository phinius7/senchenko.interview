package senchenko.interview.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import senchenko.interview.entities.views.CommonView;
import senchenko.interview.entities.views.ScheduleView;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "classroom")
    @JsonView(ScheduleView.ScheduleInfo.class)
    private String classroom;

}
