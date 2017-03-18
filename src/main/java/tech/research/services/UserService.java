package tech.research.services;

import tech.research.data.User;

/**
 * Created by lbt05 on 18/03/2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User findById(long id);
}
