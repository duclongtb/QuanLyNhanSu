package Model;

/**
 *
 * @author Iroha
 */
public class ComboItem {
    private String key;
    private String value;

    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

//    @Override
//    public boolean equals(Object obj) {
//        int a = this.getValue().compareTo(((ComboItem)obj).getValue());
//        if (a==0)
//            return true;
//        else
//            return false;
//    }
}
