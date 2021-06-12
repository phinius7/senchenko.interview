package senchenko.interview.services;

import senchenko.interview.entities.Presentation;

import java.util.List;
import java.util.Optional;

public interface PresentationService {

    void savePresentation(Presentation presentation);

    List<Presentation> findAllPresentations();

    Optional<Presentation> findPresentationById(Long id);

    void deletePresentationById(Long id);
}
