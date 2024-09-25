package app;

import app.DAOs.PoemDAO;
import app.config.HibernateConfig;
import app.controller.PoemController;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

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
        PoemController poemController = new PoemController(poemDAO);

        app.post("/poem", poemController::createPoem);
        app.get("/poem", poemController::getAllPoems);
        app.get("/poem/{id}", poemController::getPoemById);
        app.put("/poem/{id}", poemController::updatePoem);
        app.delete("/poem/{id}", poemController::deletePoem);
        app.start(7007);
    }
}