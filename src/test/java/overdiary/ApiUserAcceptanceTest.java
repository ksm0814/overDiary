package overdiary;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import overdiary.domain.User;
import overdiary.support.AcceptanceTest;

import static org.junit.Assert.assertTrue;

public class ApiUserAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ArticleAcceptanceTest.class);

    @Test
    public void showMyRank() throws Exception {
        User myUser = findByUserId("fleta");
        User DBUser = getResource(String.format("/api/users/rank/%d", myUser.getUserKey()), User.class, findDefaultUser());
        assertTrue(DBUser.getUserId().equals("fleta"));
    }
}
