package sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import sample.vo.UserVO;

@Mapper
public interface UserMapper {

	public int account(UserVO user)throws Exception;

	public UserVO login(UserVO user) throws Exception;

	public int emailUpdate(UserVO emailCheck)throws Exception;



}
