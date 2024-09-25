package app;

import jakarta.persistence.EntityManagerFactory;
import app.config.HibernateConfig;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("persons");
    }
}