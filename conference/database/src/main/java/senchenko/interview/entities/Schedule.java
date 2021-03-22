package senchenko.interview.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import senchenko.interview.entities.views.ScheduleView;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(ScheduleView.ScheduleInfo.class)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(ScheduleView.ScheduleInfo.class)
    private LocalDateTime endTime;

    @OneToOne
    @JoinColumn(name = "presentation_id")
    @JsonView(ScheduleView.ScheduleInfo.class)
    private Presentation presentation;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
