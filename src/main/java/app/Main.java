package app;

import app.DAOs.PoemDAO;
import app.controller.PoemController;
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
//            config.router.contextPath = "/api/poem";
            config.router.contextPath = "/api";
            config.bundledPlugins.enableRouteOverview("/routes");
        });

        PoemDAO poemDAO = new PoemDAO(emf);
        PoemController poemController = new PoemController(poemDAO);

        app.get("/poems", poemController::getAllPoems);
        app.get("/poems", poemController::createPoem);

        app.start(7007);
    }
}