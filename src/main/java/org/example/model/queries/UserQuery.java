package org.example.model.queries;

public class UserQuery {
    public final static String CREATE_TABLE = """
            create table if not exists tb_user(
            id  serial primary key,
            register_date timestamp with time zone,
            national_code varchar(10),
            firstName varchar(50),
            lastName varchar(50),
            password varchar(50),
            address varchar(200),
            role varchar(20),
            numberCard varchar(16)
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
            noCard)
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
                   noCard=?
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
            where notional_code = ? and password = ?
            """;


    public final static String CHANGE_PASS_QUERY = """
            update tb_user
            set password = ?
            where numberCard = ? and national_code = ?
            """;

}
