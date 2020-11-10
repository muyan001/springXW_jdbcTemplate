import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) throws SQLException {




        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        DataSource bean = applicationContext.getBean(DataSource.class);
        System.out.println(bean.getConnection());

        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        System.out.println(jdbcTemplate);


        /***
         *  实验2：将emp_id=5的记录的salary字段更新为1300.00
         */

//        String sql = "update employee set salary = ? WHERE emp_id = ?;";
//        int update = jdbcTemplate.update(sql, 1300.00, 5);
//        System.out.println("更新员工：影响了多少行："+update);


        /**
         *  实验3：批量插入
         */

        String batch = "INSERT INTO employee(emp_name,salary) VALUES (?,?);";

//        List<Object[]> 的长度就是要执行的次数，Object[]就是每次要执行的参数
        List<Object[]> batchInsert = new ArrayList<Object[]>();
        batchInsert.add(new Object[]{"张三","998.98"});
        batchInsert.add(new Object[]{"李四","1998.98"});
        batchInsert.add(new Object[]{"王五","2998.98"});
        batchInsert.add(new Object[]{"赵六","3998.98"});
        int[] ints = jdbcTemplate.batchUpdate(batch, batchInsert);
        for(int i :ints){
            System.out.println(i);
        }


    }
}


