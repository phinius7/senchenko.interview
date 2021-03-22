package senchenko.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senchenko.interview.entities.Presentation;

import java.util.List;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {


}
