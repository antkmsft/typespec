package type.property.valuetypes;

/**
 * Enum that will be used as a property for model EnumProperty. Non-extensible.
 */
public enum FixedInnerEnum {
    /**
     * First value.
     */
    VALUE_ONE("ValueOne"),

    /**
     * Second value.
     */
    VALUE_TWO("ValueTwo");

    /**
     * The actual serialized value for a FixedInnerEnum instance.
     */
    private final String value;

    FixedInnerEnum(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a FixedInnerEnum instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed FixedInnerEnum object, or null if unable to parse.
     */
    public static FixedInnerEnum fromString(String value) {
        if (value == null) {
            return null;
        }
        FixedInnerEnum[] items = FixedInnerEnum.values();
        for (FixedInnerEnum item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
