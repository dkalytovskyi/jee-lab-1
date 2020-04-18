package com.jdbc.lab1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataBaseManager dataBaseManager = new DataBaseManager();
        dataBaseManager.addDbDriver(DataBaseProperties.DRIVER);
        dataBaseManager.connectAtUrl(DataBaseProperties.URL, DataBaseProperties.LOGIN, DataBaseProperties.PASS);

        ArrayList<Movie> movies = dataBaseManager.getAllRows();
        for(Movie movie : movies) {
            System.out.println(movie);
        }

        dataBaseManager.disconnectFromDb();
    }
}
