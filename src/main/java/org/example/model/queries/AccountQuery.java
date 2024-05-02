package org.example.model.queries;

public class AccountQuery {
    public static final String CREATE_TABLE = """
            create table if not exists tb_account(
            id bigserial ,
            accountNumber varchar(50) primary key,
            balance double,
            cardStatus varchar,
            issueDate  timestamp with time zone,
            expireDate  timestamp with time zone,
            foreign key accountNumber references tb_user(numberCard)
            )
            """;

    public final static String SAVE_QUERY = """
            insert into tb_account(
            accountNumber,
            balance,
            cardStatus,
            issueDate,
            expireDate
            )
                        
                      
            values(?,?,?,?,?)
            """;

    public final static String UPDATE_QUERY = """
            update tb_account
              set accountNumber=?,
                   balance=?,
                   cardStatus=?,
                   issueDate=?,
                   expireDate=?
             where id=?

            """;
    public final static String DELETE_QUERY = """
            delete
            from tb_account
            where id=?
            """;

    public final static String FIND_BY_ID_QUERY = """
            select *
            from tb_account
            where id=?
            """;
    public final static String FIND_ALL_QUERY = """
            select * 
            from tb_account
            where id=?
            """;
    public final static String All_Balance_Query = """
            select balance
            from tb_account
            where accountNumber=?
            """;

    public final static String Transaction= """
            select Transaction
            from tb_account
            where accountNumber=?
            """;


}


