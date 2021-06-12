package senchenko.interview.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import senchenko.interview.entities.Presentation;
import senchenko.interview.repositories.PresentationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PresentationServiceTest {

    private PresentationRepository presentationRepository;
    private PresentationService presentationService;

    @BeforeEach
    public void init() {
        presentationRepository = mock(PresentationRepository.class);
        presentationService = new PresentationServiceImpl(presentationRepository);
    }

    @Test
    public void testFindById() {
        Presentation expectedPresentation = new Presentation();
        expectedPresentation.setId(1L);
        expectedPresentation.setTitle("The First Presentation");

        when(presentationRepository.findById(eq(1L))).thenReturn(Optional.of(expectedPresentation));

        Optional<Presentation> presentation = presentationService.findPresentationById(1L);

        assertNotNull(presentation);
        assertEquals(expectedPresentation.getId(), presentation.get().getId());
        assertEquals(expectedPresentation.getTitle(), presentation.get().getTitle());
    }
}
