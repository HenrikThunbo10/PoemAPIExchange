package app.DTOs;

import app.entities.Poem;
import lombok.Data;

@Data

public class PoemDTO
{
    private Poem poem;

    public PoemDTO(Poem poem)
    {
        this.poem = poem;
    }
}
