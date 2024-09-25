package app.controller;

import app.DTOs.PoemDTO;
import app.entities.Poem;
import app.DAOs.PoemDAO;
import io.javalin.http.Context;

import java.util.List;
import java.util.Set;

public class PoemController
{
    private PoemDAO poemDAO;

    public PoemController(PoemDAO poemDAO) {
        this.poemDAO = poemDAO;
    }

    public void getAllPoems(Context ctx) {
        Set<Poem> poems = poemDAO.getAllPoems();
        if (poems.isEmpty()) {
            ctx.status(404);
            ctx.result("No poems found");
        } else {
            ctx.status(200);
            ctx.json(poems);
        }
    }

    public void getPoemById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Poem poem = poemDAO.getPoemById(id);
        if (poem == null) {
            ctx.status(404);
            ctx.result("Poem not found");
        } else {
            ctx.status(200);
            ctx.json(poem);
        }
    }

    public void createPoem(Context ctx) {
        PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);
        Poem poem = poemDTO.getPoem();
        poemDAO.createPoem(poem);
        ctx.status(201);
        ctx.result("Poem created with ID: " + poem.getId());
    }

    public void updatePoem(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);
        Poem updatedPoem = poemDAO.updatePoem(id, poemDTO.getPoem());
        if (updatedPoem != null) {
            ctx.status(200);
            ctx.result("Poem updated");
        } else {
            ctx.status(404);
            ctx.result("Poem not found");
        }
    }

    public void deletePoem(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        poemDAO.deletePoem(id);
        if( poemDAO.getPoemById(id) == null) {
            ctx.status(200);
            ctx.result("Poem deleted");
        } else {
            ctx.status(404);
            ctx.result("Poem not found");
        }
    }
}