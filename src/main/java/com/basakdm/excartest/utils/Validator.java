package com.basakdm.excartest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * validation FuelConsumption
     * also fits: Mileage
     *
     * @param fuelСonsumption string that passes validation.
     * @return boolean.
     */
    public static boolean checkFuelConsumption(String fuelСonsumption) {
        //String fuelСonsumption = "8";
        Pattern pattern = Pattern.compile("^[0-9]{1,}$");
        Matcher matcher = pattern.matcher(fuelСonsumption);
        return test(matcher);
    }

    /**
     * validation Year
     *
     * @param year string that passes validation.
     * @return boolean.
     */
    public static boolean checkYear(String year) {
        //String year = "1988";
        Pattern pattern = Pattern.compile("^\\d{4}$");
        Matcher matcher = pattern.matcher(year);
        return test(matcher);
    }

    /**
     * validation Brand
     *
     * @param brand string that passes validation.
     * @return boolean.
     */
    public static boolean checkBrand(String brand) {
        //String brand = "BMW";
        Pattern pattern = Pattern.compile("^[A-za-z]{1,}$");
        Matcher matcher = pattern.matcher(brand);
        return test(matcher);
    }

    /**
     * validation ExpirationDate
     *
     * @param date string that passes validation.
     * @return boolean.
     */
    public static boolean checkExpirationDate(String date) {
        //String expirtyDate = "05.2019";
        Pattern pattern = Pattern.compile("^(0\\d|1[012])\\.(\\d{4})$");
        Matcher matcher = pattern.matcher(date);
        return test(matcher);
    }

    /**
     * validation SeriesPasport
     *
     * @param seriesPassport string that passes validation.
     * @return boolean.
     */
    public static boolean checkSeriesPasport(String seriesPassport) {
        //String seriesPassport = "BM";
        Pattern pattern = Pattern.compile("^(?:AB|BM|HB|KH|MP|MC|KB|PP){1}$");
        Matcher matcher = pattern.matcher(seriesPassport);
        return test(matcher);
    }

    /**
     * validation Date
     *
     * @param date string that passes validation.
     * @return boolean.
     */
    public static boolean checkDate(String date) {
        //String date = "03.05.1993";
        Pattern pattern = Pattern.compile("^(((0[1-9]|[12]\\d|3[01])\\.(0[13578]|1[02])\\.((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\.(0[13456789]|1[012])\\.((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\.02\\.((19|[2-9]\\d)\\d{2}))|(29\\.02\\.((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
        Matcher matcher = pattern.matcher(date);
        return test(matcher);
    }

    /**
     * validation serialDriveDoc
     *
     * @param serialDrive string that passes validation.
     * @return boolean.
     */
    public static boolean checkSerialDriveDoc(String serialDrive) {
        //String serialDriveDoc = "AA2";
        Pattern pattern = Pattern.compile("^[0-9A-z]{1,3}$");
        Matcher matcher = pattern.matcher(serialDrive);
        return test(matcher);
    }

    /**
     * validation NumDriveDoc
     *
     * @param numDoc string that passes validation.
     * @return boolean.
     */
    public static boolean checkNumDriveDoc(String numDoc) {
        //String numDoc = "635992";
        Pattern pattern = Pattern.compile("^[0-9]{1,6}$");
        Matcher matcher = pattern.matcher(numDoc);
        return test(matcher);
    }

    /**
     * validation PhoneNum
     *
     * @param phoneNum string that passes validation.
     * @return boolean.
     */
    public static boolean checkPhoneNum(String phoneNum) {
        Pattern pattern = Pattern.compile("^((\\+?375)([0-9]){9})$");
        Matcher matcher = pattern.matcher(phoneNum);
        return test(matcher);
    }

    /**
     * validation Email
     *
     * @param email string that passes validation.
     * @return boolean.
     */
    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "@"
                + "[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return test(matcher);
    }

    /**
     * validation Name
     *
     * @param name string that passes validation.
     * @return boolean.
     */
    public static boolean checkName(String name) {
        Pattern pattern = Pattern.compile("^[А-Яа-я]{4,16}$");
        Matcher matcher = pattern.matcher(name);
        return test(matcher);
    }

    /**
     * validation Password
     *
     * @param password string that passes validation.
     * @return boolean.
     */
    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,16}$");
        Matcher matcher = pattern.matcher(password);
        return test(matcher);
    }

    /**
     * validation NumPassport
     *
     * @param numPassport string that passes validation.
     * @return boolean.
     */
    public static boolean checkNumPassport(String numPassport) {
        Pattern pattern = Pattern.compile("^[0-9]{1,7}$");
        Matcher matcher = pattern.matcher(numPassport);
        return test(matcher);
    }

    /**
     * validation whoGetPasport
     *
     * @param whoGetPasport string that passes validation.
     * @return boolean.
     */
    public static boolean checkWhoGetPasport(String whoGetPasport) {
        Pattern pattern = Pattern.compile("^[А-яа-я\\s]{1,60}$");
        Matcher matcher = pattern.matcher(whoGetPasport);
        return test(matcher);
    }

    /**
     * validation WhoGetDriveDoc
     *
     * @param whoGetDriveDoc string that passes validation.
     * @return boolean.
     */
    public static boolean checkWhoGetDriveDoc(String whoGetDriveDoc) {
        Pattern pattern = Pattern.compile("^[А-яа-я\\s]{1,60}$");
        Matcher matcher = pattern.matcher(whoGetDriveDoc);
        return test(matcher);
    }

    /**
     * validation birthPlace
     *
     * @param birthPlace string that passes validation.
     * @return boolean.
     */
    public static boolean checkBirthPlace(String birthPlace) {
        Pattern pattern = Pattern.compile("^[А-яа-я\\s]{1,60}$");
        Matcher matcher = pattern.matcher(birthPlace);
        return test(matcher);
    }

    private static boolean test(Matcher matcher) {
        if (matcher.matches()) {
            System.out.println("is correct");
            return true;
        } else {
            System.out.println("is incorrect");
            return false;
        }
    }
}
