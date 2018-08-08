package com.basakdm.excartest.utils;

import java.sql.*;

public abstract class _RequestControl {

    protected Connection connection;
    protected Statement statement;
    protected String username = "root";
    protected String password = "22256";
    protected String connectionUrl = "jdbc:mysql://localhost:3336/new_schema_test?serverTimezone=UTC&useSSL=false";

    _RequestControl(){
        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось создать Connection, Statement");
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void firstQueries() {

        /*dropAllTabs(statement);

        createTableUser(statement);
        setDataUser(statement);

        createTableCars(statement);
        setDataCars(statement);

        createTableNotifyBoss(statement);
        setDataNotifyBoss(statement);

        createTableNotifyAdmin(statement);
        setDataNotifyAdmin(statement);

        createTableNotifyUser(statement);
        setDataNotifyUser(statement);

        outputUsers(statement);*/
    }


    protected void createTableUser(Statement statement)  {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                    "last_name CHAR(30), first_name CHAR(30), middle_name CHAR(30), password VARCHAR(25)," +
                    "mail VARCHAR(25), series_passport VARCHAR(25), number_seria INTEGER, who_get_pasport VARCHAR(25), when_get_passport DATE, " +
                    "birth_date DATE, birth_place VARCHAR(25), serial_drive_doc VARCHAR(25), num_drive_doc INTEGER, who_get_drive_doc VARCHAR(25), " +
                    "when_get_drive_doc DATE, expirty_date DATE, category_drive_doc VARCHAR(25), photo VARCHAR(25), " +
                    "phone_num VARCHAR(25), notify BOOlEAN, id_car INTEGER, price INTEGER, price_add INTEGER, fin_price INTEGER," +
                    "time_for_drive DATE, cause_add_price VARCHAR(200), type_user VARCHAR(25), PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Запрос на создание UserTest = Провал");
            e.printStackTrace();
        }
    }

    protected void setDataUser(Statement statement) {
        try {
            statement.executeUpdate(
                    "INSERT INTO Users(" +
                            "last_name,first_name, middle_name, password, " +
                            "mail, series_passport, number_seria, who_get_pasport, when_get_passport, " +
                            "birth_date, birth_place, serial_drive_doc, num_drive_doc, who_get_drive_doc, " +
                            "when_get_drive_doc, expirty_date, category_drive_doc, photo, " +
                            "phone_num, notify, id_car, price, price_add, fin_price, " +
                            "time_for_drive, cause_add_price, type_user) " +
                            "VALUES (" +
                            "'Карпович','Дмитрий','Иванович','qwerty8'," +
                            "'mail@hotmail.com', 'KH', 789456, 'РОВД', '2008/10/23', " +
                            "'2008/10/23', 'Гродно', 'QT', 12345678, 'Гаи', " +
                            "'2008/10/23', '2008/10/23', 'AB', 'www.photo.ru', " +
                            "333122878, true, 1, 6, 14, 20, " +
                            "'2008/10/23', 'Пользватель поцарапал машину, поэтому он платит на 6 долларов больше', 'boss')");
        } catch (SQLException e) {
            System.out.println("Заполнение UserTest = Провал");
            e.printStackTrace();
        }
    }

    protected void createTableCars(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Cars (id MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                    "brand VARCHAR(25), model VARCHAR(25), year INTEGER, state_num VARCHAR(25), mileage INTEGER, seats INTEGER, location VARCHAR(100), photo VARCHAR(25), " +
                    "transmission VARCHAR(25), car_body VARCHAR(25), drive_gear VARCHAR(25), type_engine VARCHAR(25), fuels  VARCHAR(40), fuel_consumption INTEGER, " +
                    "short_description VARCHAR(250), insurance VARCHAR(100),  text VARCHAR(250), price_lease INTEGER, calendar_of_free DATE, user_id_boss INTEGER, " +
                    "user_id_user INTEGER, free BOOLEAN, PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Запрос на создание Cars = Провал");
            e.printStackTrace();
        }
    }

    protected void setDataCars(Statement statement) {
        try {
            statement.executeUpdate(
                    "INSERT INTO `car_sharing`.`cars` (`id`, `brand`, `model`, `year`, `state_num`, `mileage`, `seats`, " +
                            "`location`, `photo`, `transmission`, `car_body`, `drive_gear`, `type_engine`, `fuels `, `fuel_consumption`, " +
                            "`short_description`, `insurance`, `text`, `price_lease`, `calendar_of_free`, `user_id_boss`, `user_id_user`, ` free`) " +
                            "VALUES (" +
                            "'1', 'BMW', '320 (e46)', '2001', '5431 ВН-4', '180000', '5', 'г. Гродно, ул. Домбровского', 'www.photo.ru', 'Manual', " +
                            "'Sedan', 'rear-wheel-drive', 'ICE', 'Benzine', '8', 'Кондиционер, кожаный салон', 'БелГосСтрах', " +
                            "'Авто на ходу. Все исправно', '60', '2008/10/23', '1', '1', '2')");
        } catch (SQLException e) {
            System.out.println("Заполнение Cars = Провал");
            e.printStackTrace();
        }
    }

    protected void createTableNotifyBoss(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS NotifyBoss (id MEDIUMINT NOT NULL AUTO_INCREMENT, text_notify VARCHAR(250), type_of_notify INTEGER, PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Запрос на создание NotifyBoss = Провал");
            e.printStackTrace();
        }
    }

    protected void setDataNotifyBoss(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO  NotifyBoss(text_notify, type_of_notify) VALUES ('Подтвердите стомость аренды', 2)");
        } catch (SQLException e) {
            System.out.println("Заполнение NotifyBoss = Провал");
            e.printStackTrace();
        }
    }

    protected void createTableNotifyAdmin(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS NotifyAdmin (id MEDIUMINT NOT NULL AUTO_INCREMENT, text_notify VARCHAR(250), id_user_plea INTEGER, PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Запрос на создание NotifyAdmin = Провал");
            e.printStackTrace();
        }
    }

    protected void setDataNotifyAdmin(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO NotifyAdmin (text_notify, id_user_plea) VALUES ('Заявка на перерасчет цены', 1)");
        } catch (SQLException e) {
            System.out.println("Заполнение NotifyAdmin = Провал");
            e.printStackTrace();
        }
    }

    protected void createTableNotifyUser(Statement statement) {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS NotifyUser (id MEDIUMINT NOT NULL AUTO_INCREMENT, text_notify VARCHAR(250), id_user_plea INTEGER, PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Запрос на создание NotifyUser = Провал");
            e.printStackTrace();
        }
    }

    protected void setDataNotifyUser(Statement statement) {
        try {
            //
            statement.executeUpdate("INSERT INTO  NotifyUser (text_notify, id_user_plea) VALUES ('Заявка на бронирование одобрена' ,1)");
        } catch (SQLException e) {
            System.out.println("Заполнение NotifyUser = Провал");
            e.printStackTrace();
        }
    }

    protected void outputUsers(Statement statement) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("last_name"));
                System.out.println("-------------");
            }
            System.out.println("_______________");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void dropAllTabs(Statement statement) {
        try {
            statement.execute("DROP TABLE IF EXISTS Users");
            statement.execute("DROP TABLE IF EXISTS Cars");
            statement.execute("DROP TABLE IF EXISTS NotifyUser");
            statement.execute("DROP TABLE IF EXISTS NotifyAdmin");
            statement.execute("DROP TABLE IF EXISTS NotifyBoss");
        } catch (SQLException e) {
            System.out.println("dropAllTabs = Провал");
            e.printStackTrace();
        }

    }
}
