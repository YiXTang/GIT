package templates;

import com.teaplantation.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "UserMapper")
public interface UserMapper {
    List<User> queryUsers();
    User queryUserByUid(int uid);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(int uid);
    int deleteUserBySid(int uid);
    int updateUserUidToNull(int uid);
}
