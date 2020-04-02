package plus.ldl.dao.impl;

import plus.ldl.dao.UserDao;
import plus.ldl.domain.User;

import java.util.*;

/**
 * @author ldl.plus
 * @date 2020年03月08日  10:14
 */
public class UserDaoImpl implements UserDao {
    private String username;
    private String[] strArray;
    private List<String> list;
    private Set<String> stringSet;
    private Map<String, User> userMap;
    private List<User> userList;

    public void setStrArray(String[] strArray) {
        this.strArray = strArray;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    private Properties properties;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void save() {
        System.out.println("UserDaoImpl.save......");
        System.out.println("username = " + username);
        System.out.println("list = " + list);
        System.out.println("userMap = " + userMap);
        System.out.println("properties = " + properties);
        System.out.println("strArray = " + Arrays.toString(strArray));
        System.out.println("stringSet = " + stringSet);
        System.out.println("userList = " + userList);
    }
}
