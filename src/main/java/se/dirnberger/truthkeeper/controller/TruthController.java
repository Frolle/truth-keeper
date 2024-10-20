package se.dirnberger.truthkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dirnberger.truthkeeper.model.Truth;
import se.dirnberger.truthkeeper.service.TruthService;

import java.util.List;

@RestController
@RequestMapping("/api/truths")
public class TruthController {
    private final TruthService truthService;

    @Autowired
    public TruthController(TruthService truthService) {
        this.truthService = truthService;
    }

    @PostMapping
    public ResponseEntity<Truth> createTruth(@RequestBody Truth truth) {
        return ResponseEntity.ok(truthService.saveTruth(truth));
    }

    @GetMapping
    public ResponseEntity<List<Truth>> getAllTruths() {
        return ResponseEntity.ok(truthService.getAllTruths());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truth> getTruthById(@PathVariable Long id) {
        return ResponseEntity.ok(truthService.getTruthById(id));
    }

    @GetMapping("/random-truth")
    public ResponseEntity<Truth> getRandomTruth() {
        return ResponseEntity.ok(truthService.findRandomTruth());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Truth> updateTruth(@PathVariable Long id, @RequestBody Truth truth) {
        return ResponseEntity.ok(truthService.updateTruth(id, truth));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTruth(@PathVariable Long id) {
        truthService.deleteTruth(id);
        return ResponseEntity.ok().build();
    }

}
