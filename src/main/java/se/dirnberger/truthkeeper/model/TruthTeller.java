package se.dirnberger.truthkeeper.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "truth_teller")
@Getter
@Setter
@EqualsAndHashCode
public class TruthTeller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "teller", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Truth> truths;
}
