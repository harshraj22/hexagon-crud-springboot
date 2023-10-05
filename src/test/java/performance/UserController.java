package performance;


// 2

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class UserController extends Simulation { // 3

    HttpProtocolBuilder httpProtocol = http // 4
            .baseUrl("http://localhost:8081/v1/") // 5
            .acceptHeader("text/html,application/xhtml+xml,application/xml,application/json;q=0.9,*/*;q=0.8") // 6
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ScenarioBuilder createAuthCredsSimulation = scenario("Create AuthCreds")
            .exec(http("Create AuthCreds")
                    .post("/authcreds")
                    .header("Content-Type", "application/json")
                    .body(StringBody("{\n" +
                            "  \"username\": \"first\",\n" +
                            "  \"password\": \"first\"\n" +
                            "}"))
            )
            .pause(1)
            .exec(http("Get AuthCreds")
                    .get("/authcreds/first")); // Replace "someUsername" with an actual username


    {
        setUp(
                createAuthCredsSimulation.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol);
    }
}
//public class ComputerDatabaseSimulation extends Simulation {
//
//    HttpProtocolBuilder httpProtocol =
//            http.baseUrl("http://localhost:8081")
//                    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
//                    .acceptLanguageHeader("en-US,en;q=0.5")
//                    .acceptEncodingHeader("gzip, deflate")
//                    .userAgentHeader(
//                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
//                    );
//
////    ScenarioBuilder users = scenario("Users").exec(search, browse);
////    ScenarioBuilder admins = scenario("Admins").exec(search, browse, edit);
//////
////    {
////        setUp(
////                users.injectOpen(rampUsers(10).during(10)),
////                admins.injectOpen(rampUsers(2).during(10))
////        ).protocols(httpProtocol);
////    }
//}