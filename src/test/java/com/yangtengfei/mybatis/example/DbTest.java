/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.yangtengfei.mybatis.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbTest {

  //https://www.bilibili.com/video/BV1BC4y1H7z5?p=5
  //https://mybatis.org/mybatis-3/zh/getting-started.html
  @Test
  public void test() throws IOException, InterruptedException {
    String resource = "com/yangtengfei/mybatis/example/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);

    /*CountDownLatch countDownLatch = new CountDownLatch(50);
    for (int i = 0; i < 50; i++) {
      fixedThreadPool.execute(new Runnable() {
        @Override
        public void run() {
          //开启一个sqlSession
          try (SqlSession session = sqlSessionFactory.openSession()) {
            Student blog = (Student) session.selectOne("com.yangtengfei.mybatis.example.StudentMapper.selectStudent", 1);
            System.out.println(blog);
          } catch (Exception e) {
            e.printStackTrace();
          }finally {
            countDownLatch.countDown();
          }
        }
      });
    }
    countDownLatch.await();*/

    for (int i = 0; i < 2; i++) {
      try (SqlSession session = sqlSessionFactory.openSession()) {
        Student studentList2 = (Student) session.selectOne("com.yangtengfei.mybatis.example.StudentMapper.selectStudent", 1);
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.selectStudent(95);
        System.out.println(studentList);


//        Student student = new Student();
//        student.setDeptId(i);
//        int id = session.insert("com.yangtengfei.mybatis.example.StudentMapper.insertUser",student);
//        session.commit();
//        System.out.println(student.getUid());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
//        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
  }
}
