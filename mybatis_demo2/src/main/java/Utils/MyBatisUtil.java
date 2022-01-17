package Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.misc.Unsafe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

//对外提供 session
public class MyBatisUtil {

    private static SqlSessionFactory factory;

    private static final ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();

    //静态代码块  执行一次
    static {
        try{
            //加载 mybatis 配置文件
            InputStream is = Resources.getResourceAsStream("mybatis_config.xml");

            //会话工厂
            factory = new SqlSessionFactoryBuilder().build(is);
        }catch (IOException E){
            E.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){

        SqlSession sqlSession = local.get();

        //判断sqlSession是有获取到
        if(sqlSession == null){
            sqlSession = factory.openSession();
            local.set(sqlSession);
        }

        return sqlSession;
    }

    public static <T extends Object>T getMapper(Class<T> c){
        SqlSession sqlSession = getSqlSession();

        T dao = sqlSession.getMapper(c);

        return dao;
    }

}
