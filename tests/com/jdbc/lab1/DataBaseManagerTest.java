package com.jdbc.lab1;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DataBaseManagerTest {
    static DataBaseManager dataBaseManager;
    ArrayList<Movie> movies;

    @BeforeAll
    public static void init() {
        dataBaseManager = new DataBaseManager();
        dataBaseManager.addDbDriver(DataBaseProperties.DRIVER);
        dataBaseManager.connectAtUrl(DataBaseProperties.URL, DataBaseProperties.LOGIN, DataBaseProperties.PASS);
    }

    @AfterAll
    public static void finalise() {
        dataBaseManager.disconnectFromDb();
    }

    @BeforeEach
    public void setUp() {
        movies = dataBaseManager.getAllRows();
    }

    @Test
    public void insertTest(){
        int res = dataBaseManager.insert(new Movie("Fight Club", "David Fincher", 1999, 8.8));
        Assert.assertEquals(1, res);
        ArrayList<Movie> movies1 = dataBaseManager.getAllRows();
        Assert.assertEquals(movies.size() + 1, movies1.size());
    }

    @Test
    public void updateTest(){
        Movie tm = movies.get(movies.size() - 1);
        double imdbRate = tm.getImdbRate();
        tm.setImdbRate(imdbRate - 1);
        Assert.assertEquals(dataBaseManager.update(tm), 1);
        Assert.assertEquals(imdbRate - 1, tm.getImdbRate(), 0);
    }

    @Test
    public void deleteTest(){
        Movie tm = movies.get(movies.size() - 1);
        int size = movies.size();
        Assert.assertEquals(dataBaseManager.delete(tm), 1);
        Assert.assertEquals(size - 1, dataBaseManager.getAllRows().size());
    }
}