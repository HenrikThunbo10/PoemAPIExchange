package app;

import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import app.config.HibernateConfig;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poemapi");

        var app = Javalin.create((config) ->
        {
            config.router.contextPath = "/api/dog";
            config.bundledPlugins.enableRouteOverview("/routes");
        });
        //app.get("/", dogController::getALlDogs);
        app.start(7007);

    }
}