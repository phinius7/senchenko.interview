package senchenko.interview.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import senchenko.interview.entities.views.CommonView;
import senchenko.interview.entities.views.ScheduleView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "listeners_presentations",
            joinColumns = @JoinColumn(name = "listener_id"),
            inverseJoinColumns = @JoinColumn(name = "presentation_id"))
    private List<Presentation> presentations;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
