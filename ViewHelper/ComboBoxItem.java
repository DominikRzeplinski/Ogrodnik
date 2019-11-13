package ViewHelper;

public class ComboBoxItem
{
    private int key;
    private String value;

    public ComboBoxItem(int key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }

    public int getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}