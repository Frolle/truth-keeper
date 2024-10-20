package se.dirnberger.truthkeeper.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dirnberger.truthkeeper.exception.TruthNotFoundException;
import se.dirnberger.truthkeeper.model.Truth;
import se.dirnberger.truthkeeper.repository.TruthRepository;

import java.util.List;

@Service
public class TruthService {

    public static final String NOT_FOUND_MESSAGE = "Truth not found with id: ";
    private final TruthRepository truthRepository;

    @Autowired
    public TruthService(TruthRepository truthRepository) {
        this.truthRepository = truthRepository;
    }

    @Transactional
    public Truth saveTruth(Truth truth) {
        return truthRepository.save(truth);
    }

    public List<Truth> getAllTruths() {
        return truthRepository.findAll();
    }

    public Truth getTruthById(Long id) {
        return truthRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MESSAGE + id));
    }

    @Transactional
    public Truth updateTruth(Long id, Truth newTruth) {
        return truthRepository.findById(id)
                .map(oldTruth -> mapTruth(oldTruth, newTruth))
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MESSAGE + id));
    }

    private Truth mapTruth(Truth oldTruth, Truth newTruth) {
        oldTruth.setTeller(newTruth.getTeller());
        oldTruth.setStatement(newTruth.getStatement());
        return truthRepository.save(oldTruth);
    }

    @Transactional
    public void deleteTruth(Long id) {
        if (truthRepository.existsById(id)) {
            truthRepository.deleteById(id);
        } else {
            throw new TruthNotFoundException(NOT_FOUND_MESSAGE + id);
        }
    }
}
