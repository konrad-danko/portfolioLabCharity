package pl.coderslab.charity.model;

public interface UserService {

    User findByEmail(String email);

    void saveUser(User user);
}
