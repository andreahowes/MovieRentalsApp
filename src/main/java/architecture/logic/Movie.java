package architecture.logic;

public class Movie {
    private String title;
    private String actor;
    private String genre;
    private int year;
    private int movie_ID;
    private int pricePerDay;

    public int getPricePerDay() {
        return pricePerDay;
    }

    public String getTitle() {
        return title;
    }

    public String getActor() {
        return actor;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getMovie_ID() {
        return movie_ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMovie_ID(int movie_ID) {
        this.movie_ID = movie_ID;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", actor='" + actor + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", movie_ID=" + movie_ID +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
