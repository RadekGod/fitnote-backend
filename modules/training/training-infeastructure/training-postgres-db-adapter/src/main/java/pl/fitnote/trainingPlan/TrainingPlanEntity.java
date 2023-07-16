//package pl.fitnote.trainingPlan;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "training_plan", schema = "training")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter(AccessLevel.PACKAGE)
//@Builder
//class TrainingPlanEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_sequence_generator")
//    @SequenceGenerator(name = "training_plan_sequence_generator",
//            sequenceName = "training_plan_id_seq",
//            allocationSize = 1,
//            schema = "training_plan"
//    )
//    private Long id;
//
//    private String name;
//
//    @Column(nullable = false)
//    private Timestamp creationDateTime;
//
//    private String note;
//}
