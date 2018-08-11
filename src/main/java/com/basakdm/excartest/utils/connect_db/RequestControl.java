package com.basakdm.excartest.utils.connect_db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public abstract class RequestControl {

    protected Connection connection;
    protected Statement statement;
    protected String username = "root";
    protected String password = "Karpovich9";
    protected String connectionUrl = "jdbc:mysql://localhost:3306/car_sharing?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    RequestControl() {
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
        try{
            statement.executeUpdate("INSERT INTO `car_sharing`.`user`" +
                    "(`id`," +
                    "`birth_date`," +
                    "`birth_place`," +
                    "`category_drive_doc`," +
                    "`cause_add_price`," +
                    "`expirty_date`," +
                    "`fin_price`," +
                    "`first_name`," +
                    "`id_car`," +
                    "`last_name`," +
                    "`mail`," +
                    "`middle_name`," +
                    "`notify`," +
                    "`num_drive_doc`," +
                    "`number_seria`," +
                    "`password`," +
                    "`phone_num`," +
                    "`photo`," +
                    "`price`," +
                    "`price_add`," +
                    "`serial_drive_doc`," +
                    "`series_passport`," +
                    "`time_for_drive`," +
                    "`type_of_notify`," +
                    "`type_user`," +
                    "`when_get_drive_doc`," +
                    "`when_get_passport`," +
                    "`who_get_drive_doc`," +
                    "`who_get_pasport`)" +
                    "VALUES" +
                    "(null," +
                    "null," +
                    "'minsc'," +
                    "'b'," +
                    "1," +
                    "null," +
                    "null," +
                    "'Basak'," +
                    "null," +
                    "null," +
                    "'xxxccc@mail.com'," +
                    "'Dmitry'," +
                    "null," +
                    "123131231," +
                    "543534," +
                    "12345," +
                    "8029777777," +
                    "null," +
                    "null," +
                    "null," +
                    "1323," +
                    "null," +
                    "null," +
                    "null," +
                    "'ADMIN'," +
                    "null," +
                    "null," +
                    "null," +
                    "null);");
        }catch (SQLException e){
            e.printStackTrace();
        }

        // Filling the table 'user' with test data
        //setDataUser(statement);

        // Filling the table 'car' with test data
        //setDataCars(statement);
    }


    /**
     * Filling the table 'user' with test data
     */
   /* protected void setDataUser(Statement statement) {
        try {
            statement.executeUpdate(
                    "INSERT INTO user(" +
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
            System.out.println("Заполнение User = Провал");
            e.printStackTrace();
        }
    }*/


    /**
     * Filling the table 'car' with test data
     */
    protected void setDataCars(Statement statement) {
        try {
            statement.executeUpdate(
                    "INSERT INTO " +
                            "`car_sharing`.`cars` (" +
                            "`brand`, `model`, `year`, `state_num`, `mileage`, `seats`, " +
                            "`location`, `photo`, `transmission`, `car_body`, `drive_gear`, `type_engine`, `fuels`, `fuel_consumption`, " +
                            "`short_description`, `insurance`, `optional_accessories`, `text`, `price_lease`, `calendar_of_free`, `user_id_boss`, `user_id_user`, `is_free`," +
                            "`is_activated`, `cause_of_rejected`) " +
                            "VALUES (" +


                            "'BMW', '320 (e46)', '2001', '5431 ВН-4', '180000', '5', " +
                            "'г. Гродно, ул. Домбровского', 'www.photo.ru', 'MANUAL', 'SEDAN', 'REAL_WHEEL_DRIVE', 'ICE', 'GAS', '8', " +
                            "'Кондиционер, кожаный салон', 'БелГосСтрах', 'Зарядка', 'Авто на ходу. Все исправно', '60', '2008/10/23', '1', '1', 'qwer'," +
                            "'q', 'w')");
        } catch (SQLException e) {
            System.out.println("Заполнение Cars = Провал");
            e.printStackTrace();
        }


        //SET `fuels` = '', `insurance` = 'f' WHERE (`id` = '1');
    }
}


