package Project;

public class Date {
    private int year;
    private int month;
    private int date;

    public Date(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) throw new IllegalArgumentException(month + " is out of bounds.");
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        if (month < 1 || month > 31) throw new IllegalArgumentException(date + " is out of bounds.");
        this.date = date;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + date;
    }
}
