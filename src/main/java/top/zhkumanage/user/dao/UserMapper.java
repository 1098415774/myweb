package top.zhkumanage.user.dao;

import java.util.List;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Repository;
import top.zhkumanage.user.entity.User;
@Repository
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_table
     *
     * @mbggenerated Sun Apr 15 15:18:17 CST 2018
     */
    int deleteByPrimaryKey(Integer iduser);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_table
     *
     * @mbggenerated Sun Apr 15 15:18:17 CST 2018
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_table
     *
     * @mbggenerated Sun Apr 15 15:18:17 CST 2018
     */
    User selectByPrimaryKey(Integer iduser);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_table
     *
     * @mbggenerated Sun Apr 15 15:18:17 CST 2018
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_table
     *
     * @mbggenerated Sun Apr 15 15:18:17 CST 2018
     */
    int updateByPrimaryKey(User record);

    User selectByUser(User user);

    User selectByUserName(String username);
}