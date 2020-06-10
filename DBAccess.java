package jovihlb.top.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAccess {

	public SqlSession getSqlSession() throws IOException{
		
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession sqlSession = factory.openSession();
		
		return sqlSession;
	}
}