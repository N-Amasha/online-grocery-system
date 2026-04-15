import java.util.ArrayList;
import java.util.Iterator;

public class UserService {
    private ArrayList<User> users = new ArrayList<>();

//    Register new user - CREATE
    public void registerUser(String username,String email,String password){
        for (User user: users){
            if(user.getEmail().equals(email)){
                System.out.println("User already exists!");
                return;
            }
        }

        User newUser = new User(username,email,password);
        users.add(newUser);
        System.out.println("User registered successfully!");
    }

//    Display Users - READ
    public void showUsers(){
        if(users.isEmpty()){
            System.out.println("No users found!");
            return;
        }
        for(User user: users){
            System.out.println(user.getUsername() + " - " + user.getEmail());
        }
    }

//    Update User details - UPDATE
    public void updateUser(String email,String newUserName,String newPassword){
        for(User user: users){
            if(user.getEmail().equals(email)){
                if(newUserName.isEmpty() || newPassword.isEmpty()){
                    System.out.println("Fields cannot be empty!");
                    return;
                }
                user.setUsername(newUserName);
                user.setPassword(newPassword);

                System.out.println("User updated successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }

//    Update Email
//    Email duplicate check
    public boolean emailExits(String email){
        for(User user: users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public void updateEmail(String oldEmail, String newEmail){
        for(User user: users){
            if(user.getEmail().equals(oldEmail)){
                if(emailExits(newEmail)){
                    System.out.println("Email already in use!");
                    return;
                }
                user.setEmail(newEmail);
                System.out.println("Email updated successfully!");
                return;
            }
        }
        System.out.println("User not found");
    }

//    Delete User - DELETE
    public void deleteUser(String email){
        /*for (User user: users){
            if(user.getEmail().equals(email)){
                users.remove(user); //can cause ConcurrentModificationException
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");*/

        Iterator<User> iterator= users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getEmail().equals(email)){
                iterator.remove(); //safe remove
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }
}
