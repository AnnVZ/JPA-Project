package spark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import spark.bd.User;
import spark.bd.UserRepo;

import static spark.Spark.*;

@SpringBootApplication
public class Demo implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    private User getUser(String name) {
        return userRepo.findOne(Example.of(new User(name, null), ExampleMatcher.matching().withIgnorePaths("id"))).get();
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        userRepo.save(new User("user", "password"));
        User u = getUser("user");
        u.addNote("note1");
        u = userRepo.save(u);
        u.removeNote(0);
        u = userRepo.save(u);

        port(8080);
        staticFiles.location("/public");
        get("/hello/:name/:age", (request, response) -> {
            response.redirect("index.html");
            return null;
        });
    }
}
