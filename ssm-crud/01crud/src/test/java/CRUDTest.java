import com.dai.entity.Employee;
import com.dai.mapper.DepartmentMapper;
import com.dai.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @description:
 * @author: dai.jf
 * @date:2021/8/28-14:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class CRUDTest {

    @Autowired
    DepartmentMapper dept;

    @Autowired
    EmployeeMapper emp;
    @Autowired
    SqlSession sqlSession;
    
    @Test
    public void test() {

        // ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        // DepartmentMapper mapper = ioc.getBean(DepartmentMapper.class);

        //        dept.insertSelective(new Department(null, "开发部"));
        //        dept.insertSelective(new Department(null, "销售部"));

        // 得到能批量的EmployeeMapper
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uu = UUID.randomUUID().toString().substring(1, 5) + "" + i;
            employeeMapper.insertSelective(new Employee(null, uu, "女", uu + "@email.com", 1));
        }
    }
}
