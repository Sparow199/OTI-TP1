package fr.ulille1.fil.odeva;

public class NonPositiveResultException extends Exception {
    NonPositiveResultException(int value1, int value2)
    {
        super(+value1+" is smaller than " + value2);
    }
}
