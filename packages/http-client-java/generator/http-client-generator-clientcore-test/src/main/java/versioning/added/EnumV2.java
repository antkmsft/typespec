package versioning.added;

/**
 * Defines values for EnumV2.
 */
public enum EnumV2 {
    /**
     * Enum value enumMember.
     */
    ENUM_MEMBER("enumMember");

    /**
     * The actual serialized value for a EnumV2 instance.
     */
    private final String value;

    EnumV2(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a EnumV2 instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed EnumV2 object, or null if unable to parse.
     */
    public static EnumV2 fromString(String value) {
        if (value == null) {
            return null;
        }
        EnumV2[] items = EnumV2.values();
        for (EnumV2 item : items) {
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
