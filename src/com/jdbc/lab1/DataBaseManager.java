package com.jdbc.lab1;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseManager {
    private Connection connection;

    public void addDbDriver(String driver){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error while trying to add driver");
        }
    }

    public void connectAtUrl(String url, String login, String pass){
        try {
            connection = DriverManager.getConnection(url, login, pass);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void disconnectFromDb(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public ArrayList<Movie> getAllRows() {
        ArrayList <Movie> movies = new ArrayList<>();

        try (Statement statement = connection.createStatement()){
            ResultSet res = statement.executeQuery(
                    "SELECT movie_id, title, director, movie_year, imdb_rate FROM movies"
            );

            while (res.next()) {
                movies.add(new Movie(
                        res.getInt("movie_id"),
                        res.getString("title"),
                        res.getString("director"),
                        res.getInt("movie_year"),
                        res.getDouble("imdb_rate")
                ));
            }

            res.close();
        } catch (SQLException e) {
            System.err.format("Select error. SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return movies;
    }

    public int insert(Movie ...movies){
        int rows = 0;
        for (Movie movie : movies) {
            try (Statement statement = connection.createStatement()){
                rows += statement.executeUpdate(
                        "INSERT INTO movies (title, director, movie_year, imdb_rate) VALUES (\'" +
                                movie.getTitle() + "\', \'" +
                                movie.getDirector() + "\', " +
                                movie.getYear() + ", " +
                                movie.getImdbRate() + ")"
                );
            } catch (SQLException e) {
                System.err.format("Insert error. SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }
        return rows;
    }

    public int update(Movie ...movies){
        int rows = 0;
        for(Movie movie : movies) {
            try (Statement statement = connection.createStatement()) {
                rows = statement.executeUpdate(
                        "UPDATE movies SET " +
                                "title = \'" + movie.getTitle() + "\', " +
                                "director = \'" + movie.getDirector() + "\', " +
                                "movie_year = " + movie.getYear() + ", " +
                                "imdb_rate = " + movie.getImdbRate() +
                        " WHERE movie_id = " + movie.getId()
                );
            } catch (SQLException e) {
                System.err.format("Update error. SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }
        return rows;
    }

    public int delete(Movie ...movies){
        int rows = 0;
        for(Movie movie : movies){
            try (Statement statement = connection.createStatement()){
                rows += statement.executeUpdate(
                        "DELETE FROM movies WHERE movie_id = " + movie.getId()
                );
            } catch (SQLException e) {
                System.err.format("Delete error. SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }
        return rows;
    }
}
