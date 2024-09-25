package app.DTOs;

import app.entities.Poem;
import app.enums.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PoemDTO
{
    private Long id;
    private String title;
    private String poem;
    private String author;
    private Type type;

    public PoemDTO(Poem poem)
    {
        this.id = poem.getId();
        this.title = poem.getTitle();
        this.poem = poem.getPoem();
        this.author = poem.getAuthor();
        this.type = poem.getType();
    }
}
