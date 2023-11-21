package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.User;

public interface UserRepository{

    void save(User user);
    void update(User user);
    User findById(Integer id);

}
