package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Student;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;

public interface StudentDao {

    //添加学生的方法
    public int insertStudent(Student student);

    //删除操作  根据 num 字段来删除
    public int deleteStudent(String stuNum);

    //修改操作 根据学号需改其他字段信息
    public int updateStudent(Student student);

    //查询操作 查询所有
    public List<Student> listStudents();

    //根据学号查询一个学生信息
    public Student queryStudent(String stuNum);

    //查询 学生 总数 方法
    public int getCount();

    //分页查询
    // public List<Student> listStudentsByPage(HashMap<String, Integer> map);
    public List<Student> listStudentsByPage(@Param("start") int start,
                                            @Param("pageSize") int pageSize);




}
