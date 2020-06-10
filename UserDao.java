package jovihlb.top.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import jovihlb.top.dao.User;
import jovihlb.top.db.DBAccess;



public class UserDao {

	public boolean checkUsername(String username){
		boolean flag = true;
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
			flag= sqlSeesion.selectOne("UserDao.checkUsername",username);
			
			//System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		
		return flag;
	}
	
	public boolean login(String username,String password){
		boolean flag = true;
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			flag= sqlSeesion.selectOne("UserDao.login",user);
			System.out.println(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		
		return flag;
	}
	public List<User> findAll(){
		List<User> users = new ArrayList<User>();		
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
			users = sqlSeesion.selectList("UserDao.findAll");
			
			//System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		
		return users;
		
	}
	public boolean update(int id,String username,String password,String email) {
		boolean f  = true;
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
//			sqlSeesion.selectList("Userinfo.findAll");
			User user = new User(id,username, password, email);			
			int i = sqlSeesion.update("UserDao.update", user);
			System.out.println(i);
			sqlSeesion.commit();
			
			//System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		return f;
		
	}
	
	public boolean delete(int id) { 
		boolean f  = true;
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
//			sqlSeesion.selectList("Userinfo.findAll");
			int i = sqlSeesion.delete("UserDao.delete", id);
//			System.out.println(i);
			sqlSeesion.commit();
			
			//System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		return f;
	}
	
	public User findById(int id){
		User user = null;
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
			user = sqlSeesion.selectOne("UserDao.find", id);
			//System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		return user;
		
	}
public boolean insert(String username,String password,String email){
		
		boolean flag = true;
		SqlSession sqlSeesion = null;
		try {
			sqlSeesion = new DBAccess().getSqlSession();
//			sqlSeesion.selectList("Userinfo.findAll");
			User user = new User(username, password, email);			
			sqlSeesion.update("UserDao.insert", user);
//			System.out.println(i);
			sqlSeesion.commit();
			
			//System.out.println(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (sqlSeesion!=null) {
				sqlSeesion.close();
			}		
		}
		return flag;
	}
}
