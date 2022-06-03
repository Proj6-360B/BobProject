package Project;

import java.io.Serializable;

/**
 * Stores Year, Month, & Day ints.
 * @author David Huynh
 */
public class Date implements Serializable  {
    /**
     * serialVersionUID so it doesnt auto generate and it farts during deserialize.
     */
    private static final long serialVersionUID = 91021L;
    private int year;
    private int month;
    private int day;

    public Date(String year, String month, String date) {
        this.year = Integer.valueOf(year);
        this.month = Integer.valueOf(month);
        this.day = Integer.valueOf(date);
    }

    public Date(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.day = date;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (month < 1 || month > 31) throw new IllegalArgumentException(day + " is out of bounds.");
        this.day = day;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        sb.append('/');
        sb.append(month < 10 ? "0" + month : month);
        sb.append('/');
        sb.append(day < 10 ? "0" + day : day);
        return sb.toString();
    }
}
