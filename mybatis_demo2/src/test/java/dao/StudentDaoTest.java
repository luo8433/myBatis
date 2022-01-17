package dao;

import Utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import pojo.Student;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

class StudentDaoTest {

    //忽略 jdk 版本 提示
    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }




    //添加方法的测试
    @Test
    public void insertStudent() {

            disableWarning();

            SqlSession sqlSession = MyBatisUtil.getSqlSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            Student student = new Student(0, "10009", "根华", "男", 26);

            //调用 添加 insertStudent 方法
            int i = studentDao.insertStudent(student);

            System.out.println(student);

            sqlSession.commit();
    }

    //删除方法的测试
    @Test
    public void testDeleteStudent(){

        disableWarning();

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

        System.out.println(studentDao);

        int i = studentDao.deleteStudent("10001");

        System.out.println(i);

        sqlSession.commit();

    }

    //更新方法
    @Test
    public void testUpdateStudent(){

            disableWarning();

            SqlSession sqlSession = MyBatisUtil.getSqlSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            int i = studentDao.updateStudent(new Student(0, "10002", "华华华", "男", 19));

            System.out.println(i);

            sqlSession.commit();
    }

    //查询所有字段方法测试
    @Test
    public void testListStudents() {

        disableWarning();

        StudentDao studentDao = MyBatisUtil.getMapper(StudentDao.class);

        List<Student> list = studentDao.listStudents();

        for (Student stu:list) {
            System.out.println(stu);
        }

    }

    //根据学号查询一个学生信息测试
    @Test
    public void testQueryStudent(){

        disableWarning();

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

        Student student = studentDao.queryStudent("10002");

        System.out.println(student);

    }


    //查询 总共 学生数的 测试方法
    @Test
    public void testGetCount(){

        disableWarning();

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

        int count = studentDao.getCount();

        System.out.println(count);
    }


    //分页查询  从第 start 起开始查  pageSize 个
    @Test
    public void testListStudentsByPage(){

        disableWarning();

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

//            HashMap<String, Integer> map = new HashMap<>();
//            map.put("start",0);
//            map.put("pageSize",2);
//            List<Student> list = studentDao.listStudentsByPage(map);

        List<Student> list = studentDao.listStudentsByPage(0,2);

        for (Student stu:list) {
            System.out.println(stu);
        }
    }

}