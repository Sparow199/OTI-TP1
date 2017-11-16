package fr.ulille1.fil.odeva;

public class NonPositiveValueException extends Exception {
    NonPositiveValueException(int value)
    {
        super(value+" is negative !!!");
    }
}
