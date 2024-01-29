package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.Annotation.AutoFill;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert(value = "insert into employee(name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user) values" +
                                        " (#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(getOperationType = OperationType.INSERT)
    void insert(Employee employee);

    Page<Employee> queryForPageWithName(@Param("name") String name);

//    @Update("update employee SET status = #{status} where id = #{id}")
//    void reviseStatus(Long id, Integer status);

    @AutoFill(getOperationType = OperationType.UPDATE)
    void reviseStatus(Employee employee);
}
