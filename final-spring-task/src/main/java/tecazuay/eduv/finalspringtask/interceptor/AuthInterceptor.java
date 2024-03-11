package tecazuay.eduv.finalspringtask.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String requestSession = (String) session.getAttribute("idCard");

        if (requestSession == null || requestSession.isEmpty()) {
            response.sendRedirect("/user/login");
            return false; // No continuar con el flujo hacia el controlador
        }

        return true; // Continuar con el flujo hacia el controlador si la sesión es válida
    }
}
