package cn.yfz.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.yfz.dao.PrivilegeDao;
import cn.yfz.domain.Function;
import cn.yfz.domain.Role;
import cn.yfz.domain.User;
import cn.yfz.utils.DBCPUtils;

public class PrivilegeDaoImpl implements PrivilegeDao {
	private QueryRunner qr=new QueryRunner(DBCPUtils.getDataSource());
	@Override
	public User find(String username, String password) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from users where username=? and password=?", new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Role> findRoleByUser(User user) {
		// TODO Auto-generated method stub
		if(user==null||user.getId()==null){
			throw new IllegalArgumentException("参数不全");
		}
		try {
			return qr.query("select r.* from roles r,user_role ur where r.id=ur.r_id and ur.u_id=?", new BeanListHandler<Role>(Role.class), user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Function> findFunctionByRole(Role role) {
		// TODO Auto-generated method stub
		
		if(role==null||role.getId()==null){
			throw new IllegalArgumentException("参数不全");
		}
		try {
			return qr.query("select f.* from functions f,role_function rf where f.id=rf.f_id and rf.r_id=?", new BeanListHandler<Function>(Function.class), role.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
