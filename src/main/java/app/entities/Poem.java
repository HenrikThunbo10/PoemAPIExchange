package app.entities;

import app.enums.Type;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Level;

@Entity
@Data
@NoArgsConstructor
public class Poem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String poem;
    private String author;
    private Type type;


}
