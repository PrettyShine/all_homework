package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    private SqlSession sqlSession;

    @Before
    public void initSession() throws PropertyVetoException, DocumentException {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testFindByCondition() throws Exception {

        //调用
        User userCondition = new User();
        userCondition.setId(1);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = userDao.findByCondition(userCondition);
        System.out.println(user);


    }
    @Test
    public void testAdd() throws Exception {

        User user = new User();
        user.setId(10086);
        user.setUsername("qsx");
        user.setPassword("qsx");
        user.setBirthday("2021年9月5日14:56:26");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.save(user);
        User addUser = userDao.findByCondition(user);
        System.out.println(addUser);

    }
    @Test
    public void testUpdate() throws Exception {

        User user = new User();
        user.setId(10086);
        user.setUsername("仇树祥");
        user.setPassword("qsx");
        user.setBirthday("2021年9月5日14:59:08");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.update(user);
        User addUser = userDao.findByCondition(user);
        System.out.println(addUser);

    }
    @Test
    public void testDel() throws Exception {

        User user = new User();
        user.setId(10086);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.del(user.getId());
        User addUser = userDao.findByCondition(user);
        System.out.println(addUser);

    }



}
