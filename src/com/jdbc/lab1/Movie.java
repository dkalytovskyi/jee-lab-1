package com.jdbc.lab1;

public class Movie {
    int id;
    String title;
    String director;
    int year;
    double imdbRate;

    public Movie(){}

    public Movie(String title, String director, int year, double imdbRate) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.imdbRate = imdbRate;
    }

    public Movie(int id, String title, String director, int year, double imdbRate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.imdbRate = imdbRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getImdbRate() {
        return imdbRate;
    }

    public void setImdbRate(double imdbRate) {
        this.imdbRate = imdbRate;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", imdbRate=" + imdbRate +
                '}';
    }
}
