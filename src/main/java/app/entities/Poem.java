package app.entities;

import jakarta.persistence.Access;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Level;

@Data
@NoArgsConstructor
public class Poem
{
    @Setter(AccessLevel.NONE)
    private Long id;
    private String poem;
    private String author;
    private Type type;


}
