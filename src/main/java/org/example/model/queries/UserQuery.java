package org.example.model.queries;

public class UserQuery {
    public final static String CREATE_TABLE = """
                CREATE TABLE IF NOT EXISTS tb_user (
                    id SERIAL,
                    register_date TIMESTAMP WITH TIME ZONE,
                    national_code VARCHAR(10),
                    firstName VARCHAR(50),
                    lastName VARCHAR(50),
                    password VARCHAR(50),
                    address VARCHAR(200),
                    role VARCHAR(20),
                    numberCard VARCHAR(16) PRIMARY KEY DEFAULT LPAD(CAST(FLOOR(RANDOM() * 10000000000000000) AS VARCHAR), 16, '1') NOT NULL
                )
            """;

    public final static String SAVE_QUERY = """
            insert into tb_user(
            register_date,
            national_code,
            firstName,
            lastName,
            password,
            address,
            role,
            numberCard)
            values(?,?,?,?,?,?,?,?)
            """;

    public final static String UPDATE_QUERY = """
            update tb_user
              set  national_code=?,
                   firstName=?,
                   lastName=?,
                   password=?,
                   address=?,
                   role=?,
                   numberCard=?
             where id=?

            """;
    public final static String DELETE_QUERY = """
            delete
            from tb_user
            where id=?
            """;

    public final static String FIND_BY_ID_QUERY = """
            select *
            from tb_user
            where id=?
            """;
    public final static String FIND_ALL_QUERY = """
            select * 
            from tb_user
            where id=?
            """;

    public final static String LOGIN_QUERY = """
            select role
            from tb_user
            where national_code = ? and password = ?
            """;


    public final static String CHANGE_PASS_QUERY = """
            update tb_user
            set password = ?
            where numberCard = ? and national_code = ?
            """;

}
