package app;

import app.DAOs.PoemDAO;
import app.controller.PoemController;
import app.entities.Poem;
import app.enums.Type;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import app.config.HibernateConfig;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");

        var app = Javalin.create((config) ->
        {
            config.router.contextPath = "/api";
            config.bundledPlugins.enableRouteOverview("/routes");
        });

        PoemDAO poemDAO = new PoemDAO(emf);
        Poem poem = new Poem("sf", "sdf", "sdf", Type.SONNET);
        poemDAO.createPoem(poem);

        PoemController poemController = new PoemController(poemDAO);

        app.get("/poem", poemController::getAllPoems);
        app.get("/poem/{id}", poemController::getPoemById);
        app.put("/poem/{id}", poemController::updatePoem);

        app.delete("/poem/{id}", poemController::deletePoem);




        app.start(7007);
    }
}