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
    /**
     * Year
     */
    private int year;
    /**
     * Month
     */
    private int month;
    /**
     * Day
     */
    private int day;

    /**
     * Constructor. Year, Month, Day as Strings.
     * @author David Huynh
     * @param year
     * @param month
     * @param day
     */
    public Date(String year, String month, String day) {
        this.year = Integer.valueOf(year);
        this.month = Integer.valueOf(month);
        this.day = Integer.valueOf(day);
    }

    /**
     * Constructor. Year, Month, Day as ints.
     * @author David Huynh
     * @param year
     * @param month
     * @param day
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Get Year.
     * @author David Huynh
     * @return Year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set Year.
     * @author David Huynh
     * @param year Year.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get Month.
     * @author David Huynh
     * @return Month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set Month.
     * @author David Huynh
     * @param month Month.
     */
    public void setMonth(int month) {
        if (month < 1 || month > 12) throw new IllegalArgumentException(month + " is out of bounds.");
        this.month = month;
    }

    /**
     * Get Day.
     * @author David Huynh
     * @return Day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Set Day.
     * @author David Huynh
     * @param day Day.
     */
    public void setDay(int day) {
        if (month < 1 || month > 31) throw new IllegalArgumentException(day + " is out of bounds.");
        this.day = day;
    }

    /**
     * "YYYY/MM/DD"
     * @author David Huynh
     * @return "YYYY/MM/DD"
     */
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
