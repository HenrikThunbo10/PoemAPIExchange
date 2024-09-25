package app.entities;

import app.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Poem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String poem;
    private String author;
    private Type type;


    public Poem(String title, String poem, String author, Type type)
    {
        this.title = title;
        this.poem = poem;
        this.author = author;
        this.type = type;
    }
}
