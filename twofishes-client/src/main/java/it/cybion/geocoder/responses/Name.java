package it.cybion.geocoder.responses;

import java.util.List;

/**
 * @author Matteo Moci ( matteo (dot) moci (at) gmail (dot) com )
 */
public class Name {

    private String name;

    private String lang;

    private List<Flag> flags;

    private Name() {

    }

    public String getName() {

        return name;
    }

    public String getLang() {

        return lang;
    }

    public List<Flag> getFlags() {

        return flags;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name name1 = (Name) o;

        if (flags != null ? !flags.equals(name1.flags) : name1.flags != null) {
            return false;
        }
        if (lang != null ? !lang.equals(name1.lang) : name1.lang != null) {
            return false;
        }
        if (name != null ? !name.equals(name1.name) : name1.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (flags != null ? flags.hashCode() : 0);
        return result;
    }
}
