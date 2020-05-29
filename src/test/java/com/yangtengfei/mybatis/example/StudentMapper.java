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

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.junit.runners.Parameterized;

import java.util.List;

public interface StudentMapper {

  //@Select("select * from student where uid = #{uid}")
  List<Student> selectStudent(@Param("uid") int id);


//  @Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
//  @Insert("insert into student(deptId) value(#{deptId})")
  int insertUser(int deptId);
}
