package overdiary.helper;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import overdiary.domain.User;

public class HttpSessionUtils {
    public static final String SESSION_KEY = "SESSION_KEY";

    public static boolean isLoginUser(NativeWebRequest webRequest) {
        Object loginedUser = webRequest.getAttribute(SESSION_KEY, WebRequest.SCOPE_SESSION);
        return loginedUser != null;
    }

    public static User getUserFromSession(NativeWebRequest webRequest) {
        if (!isLoginUser(webRequest)) {
            return User.GUEST_USER;
        }
        return (User) webRequest.getAttribute(SESSION_KEY, WebRequest.SCOPE_SESSION);
    }
}
