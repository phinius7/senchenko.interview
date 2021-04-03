package senchenko.interview.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senchenko.interview.entities.Presentation;
import senchenko.interview.repositories.PresentationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService {

    private final PresentationRepository presentationRepository;

    @Override
    public void savePresentation(Presentation presentation) {
        presentationRepository.save(presentation);
    }

    @Override
    public List<Presentation> findAllPresentations() {
        return presentationRepository.findAll();
    }

    @Override
    public Optional<Presentation> findPresentationById(Long id) {
        return presentationRepository.findById(id);
    }

    @Override
    public void deletePresentationById(Long id) {
        presentationRepository.deleteById(id);
    }

    public Presentation saveOrUpdate(Presentation presentation) {
        return presentationRepository.save(presentation);
    }
}
