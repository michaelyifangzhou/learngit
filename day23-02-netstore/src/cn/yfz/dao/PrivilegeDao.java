package cn.yfz.dao;

import java.util.List;

import cn.yfz.domain.Function;
import cn.yfz.domain.Role;
import cn.yfz.domain.User;

public interface PrivilegeDao {

	User find(String username, String password);

	List<Role> findRoleByUser(User user);

	List<Function> findFunctionByRole(Role role);

}
