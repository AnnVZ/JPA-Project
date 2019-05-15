package spark.bd;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String text;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public Note(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Note() {
    }
}