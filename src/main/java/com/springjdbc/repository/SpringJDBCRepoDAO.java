package com.springjdbc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springjdbc.model.SpringJDBCDemo;

@Repository
public class SpringJDBCRepoDAO {
	@Autowired
	JdbcTemplate template;
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public void save(SpringJDBCDemo springJDBCDemo)
	{
		System.out.print("added");
		String sql="insert into SpringJDBCDemo (id,name,position) values (?,?,?)";
		int rows= template.update(sql,springJDBCDemo.getId(), springJDBCDemo.getName(), springJDBCDemo.getPosition());
		System.out.println(rows + " got affected");
	}
    public void update(SpringJDBCDemo springJDBCDemo) {
        String sql = "UPDATE SpringJDBCDemo SET name = ?, position = ? WHERE id = ?";
        template.update(sql, springJDBCDemo.getName(), springJDBCDemo.getPosition(), springJDBCDemo.getId());
    }
    public void deleteAll() {
        String sql = "DELETE FROM SpringJDBCDemo";
        template.update(sql);
    }
	public List<SpringJDBCDemo> findAll()
	{
		
		String sql = "select * from SpringJDBCDemo";
		RowMapper<SpringJDBCDemo> rowmapper = (rs, rowNum) ->  {
		SpringJDBCDemo springJDBCDemo=new SpringJDBCDemo();
		springJDBCDemo.setId(rs.getInt(1));
		springJDBCDemo.setName(rs.getString(2));
		springJDBCDemo.setPosition(rs.getString(3));
		return springJDBCDemo;
		};
		List<SpringJDBCDemo> springJDBCDemos= template.query(sql, rowmapper);
		//return new ArrayList<SpringJDBCDemo>();
		return springJDBCDemos;
	}
	
    public void deleteById(int id) {
        String sql = "DELETE FROM SpringJDBCDemo WHERE id = ?";
        template.update(sql, id);
        
    }
    public SpringJDBCDemo findById(int id) {
        String sql = "SELECT id, name, position FROM SpringJDBCDemo WHERE id = ?";
		RowMapper<SpringJDBCDemo> rowmapper = (rs, rowNum) ->  {
		SpringJDBCDemo springJDBCDemo=new SpringJDBCDemo();
		springJDBCDemo.setId(rs.getInt(1));
		springJDBCDemo.setName(rs.getString(2));
		springJDBCDemo.setPosition(rs.getString(3));
		return springJDBCDemo;
		};
        return template.queryForObject(sql, new Object[]{id}, rowmapper);
        //template.query(sql,springJDBCDemo.getId(), rowmapper);
        
    }
    public Optional<SpringJDBCDemo> findByIdAnotherWay(int id) {
        String sql = "SELECT id, name, position FROM SpringJDBCDemo WHERE id = ?";
		RowMapper<SpringJDBCDemo> rowmapper = (rs, rowNum) ->  {
		SpringJDBCDemo springJDBCDemo=new SpringJDBCDemo();
		springJDBCDemo.setId(rs.getInt(1));
		springJDBCDemo.setName(rs.getString(2));
		springJDBCDemo.setPosition(rs.getString(3));
		return springJDBCDemo;
		};
        List<SpringJDBCDemo> results = template.query(sql, new Object[]{id}, rowmapper);

        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }
}
